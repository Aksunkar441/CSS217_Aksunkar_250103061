import java.util.Objects;

public class AtmFacade {
    private final CardReader cardReader;
    private final PinAuthenticator pinAuthenticator;
    private final AccountLedger ledger;
    private final CashDispenser dispenser;
    private final AuditLogger auditLogger;

    public AtmFacade(CardReader cardReader, PinAuthenticator pinAuthenticator,
                     AccountLedger ledger, CashDispenser dispenser, AuditLogger auditLogger) {
        this.cardReader = Objects.requireNonNull(cardReader);
        this.pinAuthenticator = Objects.requireNonNull(pinAuthenticator);
        this.ledger = Objects.requireNonNull(ledger);
        this.dispenser = Objects.requireNonNull(dispenser);
        this.auditLogger = Objects.requireNonNull(auditLogger);
    }

    public boolean withdrawCash(String cardNumber, int pin, double amount) {
        if (amount <= 0 || amount % 10 != 0) {
            auditLogger.log(cardNumber, amount, false, "Amount must be a positive multiple of $10");
            System.out.println("Withdrawal rejected: enter a positive amount in $10 increments.");
            return false;
        }
        if (!cardReader.readCard(cardNumber)) {
            auditLogger.log(cardNumber, amount, false, "Card not recognized");
            System.out.println("Withdrawal rejected: card not recognized.");
            return false;
        }
        if (!pinAuthenticator.authenticate(cardNumber, pin)) {
            auditLogger.log(cardNumber, amount, false, "Incorrect PIN");
            System.out.println("Withdrawal rejected: incorrect PIN.");
            return false;
        }
        if (!ledger.hasFunds(cardNumber, amount)) {
            auditLogger.log(cardNumber, amount, false, "Insufficient account balance");
            System.out.println("Withdrawal rejected: insufficient account balance.");
            return false;
        }
        if (!dispenser.hasCash(amount)) {
            auditLogger.log(cardNumber, amount, false, "ATM has insufficient cash");
            System.out.println("Withdrawal rejected: ATM has insufficient cash.");
            return false;
        }

        // Both balances are checked before either subsystem updates its state.
        ledger.debit(cardNumber, amount);
        dispenser.dispense(amount);
        auditLogger.log(cardNumber, amount, true, "Cash dispensed successfully");
        return true;
    }
}
