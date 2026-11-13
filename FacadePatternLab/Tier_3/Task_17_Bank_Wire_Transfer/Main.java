public class Main {
    public static void main(String[] args) {
        AccountService accounts = new AccountService();
        LedgerService ledger = new LedgerService();
        WireTransferFacade transfers = new WireTransferFacade(
                new ComplianceScreener(), new ExchangeRateService(), accounts, ledger);

        System.out.println("Initial source balance: " + accounts.balanceOf("ACC-USD") + " USD");
        transfers.executeTransfer("ACC-USD", "ACC-EUR", 100.00, "EUR");

        double beforeFailedTransfer = accounts.balanceOf("ACC-USD");
        transfers.executeTransfer("ACC-USD", "FROZEN-EUR", 50.00, "EUR");
        double afterFailedTransfer = accounts.balanceOf("ACC-USD");

        System.out.printf("Source balance after rollback: %.2f USD (before failed transfer: %.2f)%n",
                afterFailedTransfer, beforeFailedTransfer);
        System.out.println("Rollback restored the original debit: "
                + (Math.abs(afterFailedTransfer - beforeFailedTransfer) < 0.000001));
        System.out.println("Ledger entries: " + ledger.getEntries().size());
    }
}
