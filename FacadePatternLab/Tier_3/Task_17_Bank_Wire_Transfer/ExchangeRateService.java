import java.util.Map;

public class ExchangeRateService {
    private final Map<String, Double> rates = Map.of(
            "USD_EUR", 0.92,
            "EUR_USD", 1.09,
            "USD_KZT", 500.0,
            "KZT_USD", 0.002
    );

    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }
        Double rate = rates.get(fromCurrency + "_" + toCurrency);
        if (rate == null) {
            throw new IllegalArgumentException(
                    "No exchange rate for " + fromCurrency + " to " + toCurrency + ".");
        }
        double converted = amount * rate;
        System.out.printf("Converted %.2f %s to %.2f %s.%n",
                amount, fromCurrency, converted, toCurrency);
        return converted;
    }
}
