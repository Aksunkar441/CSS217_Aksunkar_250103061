// Subsystem 3: Converts USD amounts into the passenger's billing currency.
public class CurrencyConverter {
    public double convertFromUSD(double amountUSD, String targetCurrency) {
        if (targetCurrency == null) {
            throw new IllegalArgumentException("Target currency cannot be null.");
        }
        String code = targetCurrency.trim().toUpperCase();
        double rate;
        switch (code) {
            case "USD":
                rate = 1.0;
                break;
            case "EUR":
                rate = 0.92;
                break;
            case "GBP":
                rate = 0.79;
                break;
            case "KZT":
                rate = 485.0;
                break;
            default:
                throw new IllegalArgumentException("Unsupported currency code: " + code);
        }
        double converted = amountUSD * rate;
        System.out.printf("[CurrencyConverter] %.2f USD -> %.2f %s%n", amountUSD, converted, code);
        return converted;
    }
}