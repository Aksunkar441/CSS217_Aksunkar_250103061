// Subsystem 2: Calculates checked baggage surcharges in USD.
public class BaggagePricingEngine {
    // Base allowance is 1 bag (up to 20 kg). Extra bags or heavy bags incur fees.
    public double calculateBaggageFeeUSD(int bagCount, double totalWeightKg) {
        if (bagCount < 0 || totalWeightKg < 0) {
            throw new IllegalArgumentException("Bag count and weight cannot be negative.");
        }
        if (bagCount == 0) {
            System.out.println("[BaggageEngine] No checked bags. Fee: $0.00");
            return 0.0;
        }
        double fee = 0.0;
        if (bagCount > 1) {
            fee += (bagCount - 1) * 35.0; // $35 per additional bag
        }
        if (totalWeightKg > 20.0) {
            double extraKg = totalWeightKg - 20.0;
            fee += extraKg * 12.0; // $12 per overweight kg
        }
        System.out.printf("[BaggageEngine] Calculated baggage fee: $%.2f%n", fee);
        return fee;
    }
}