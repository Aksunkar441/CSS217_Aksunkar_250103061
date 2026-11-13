public class Main {
    public static void main(String[] args) {
        AtmFacade atm = new AtmFacade(new CardReader(), new PinAuthenticator(),
                new AccountLedger(), new CashDispenser(), new AuditLogger());

        atm.withdrawCash("4000123412341234", 1234, 100.00);
        atm.withdrawCash("4000123412341234", 9999, 50.00);
        atm.withdrawCash("5555444433331111", 4321, 200.00);
    }
}
