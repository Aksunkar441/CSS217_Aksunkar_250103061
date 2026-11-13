public class PaymentProcessor {
    public boolean chargeCustomer(double amount) {
        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return false;
        }
        System.out.printf("Payment of $%.2f approved.%n", amount);
        return true;
    }
}
