import java.util.Objects;

// Coordinates the transfer and makes sure a failed destination credit never leaves
// the source account short of the amount that was originally debited.
public class WireTransferFacade {
    private final ComplianceScreener compliance;
    private final ExchangeRateService exchangeRates;
    private final AccountService accounts;
    private final LedgerService ledger;

    public WireTransferFacade(ComplianceScreener compliance, ExchangeRateService exchangeRates,
                              AccountService accounts, LedgerService ledger) {
        this.compliance = Objects.requireNonNull(compliance);
        this.exchangeRates = Objects.requireNonNull(exchangeRates);
        this.accounts = Objects.requireNonNull(accounts);
        this.ledger = Objects.requireNonNull(ledger);
    }

    public boolean executeTransfer(String fromAcct, String toAcct,
                                   double amount, String targetCurrency) {
        System.out.println("\nProcessing transfer " + fromAcct + " -> " + toAcct + ".");
        if (targetCurrency == null || !compliance.approve(fromAcct, toAcct, amount)) {
            ledger.record("REJECTED", "Compliance or request validation failed.");
            return false;
        }

        String sourceCurrency = accounts.getCurrency(fromAcct);
        if (sourceCurrency == null) {
            ledger.record("REJECTED", "Source account does not exist.");
            return false;
        }
        if (!accounts.debit(fromAcct, amount)) {
            ledger.record("REJECTED", "Source debit failed.");
            return false;
        }

        try {
            double convertedAmount = exchangeRates.convert(amount, sourceCurrency, targetCurrency);
            if (!accounts.credit(toAcct, convertedAmount, targetCurrency)) {
                accounts.refundDebit(fromAcct, amount);
                ledger.record("ABORTED_ROLLED_BACK",
                        "Destination credit failed; exact source debit was refunded.");
                return false;
            }
            ledger.record("COMPLETED", String.format(
                    "%.2f %s transferred as %.2f %s.",
                    amount, sourceCurrency, convertedAmount, targetCurrency));
            return true;
        } catch (RuntimeException failure) {
            accounts.refundDebit(fromAcct, amount);
            ledger.record("ABORTED_ROLLED_BACK", "Transfer failed: " + failure.getMessage());
            return false;
        }
    }
}
