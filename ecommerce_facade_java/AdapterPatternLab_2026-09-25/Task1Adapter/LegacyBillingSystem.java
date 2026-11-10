public class LegacyBillingSystem {
    // Charges in whole cents (e.g., $10.50 is passed as 1050)
    public void chargeCustomerInCents(int customerId, long amountInCents) {
        System.out.println("Billed customer " + customerId + ": " + amountInCents + "cents");
    }
}