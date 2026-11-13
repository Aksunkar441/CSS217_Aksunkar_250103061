public class CashDispenser {
    private double cashReserve = 500.00;

    public boolean hasCash(double amount) { return cashReserve >= amount; }

    public boolean dispense(double amount) {
        if (!hasCash(amount)) return false;
        cashReserve -= amount;
        System.out.printf("Please take your $%.2f.%n", amount);
        return true;
    }

    public double getCashReserve() { return cashReserve; }
}
