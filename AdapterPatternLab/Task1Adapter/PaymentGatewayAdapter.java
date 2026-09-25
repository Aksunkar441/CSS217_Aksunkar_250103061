import java.math.BigDecimal;

public class PaymentGatewayAdapter implements IPaymentGateway {

    private final LegacyBillingSystem legacyBillingSystem;

    // Можно принимать legacy-объект снаружи
    public PaymentGatewayAdapter(LegacyBillingSystem legacyBillingSystem) {
        this.legacyBillingSystem = legacyBillingSystem;
    }

    // На случай, если тесты создают адаптер без параметров
    public PaymentGatewayAdapter() {
        this(new LegacyBillingSystem());
    }

    @Override
    public void processPayment(int customerId, BigDecimal amountInDollars) {
        if (amountInDollars == null) {
            throw new IllegalArgumentException("amountInDollars must not be null");
        }

        if (amountInDollars.signum() < 0) {
            throw new IllegalArgumentException("amountInDollars must not be negative");
        }

        long amountInCents = amountInDollars.multiply(BigDecimal.valueOf(100)).longValueExact();

        legacyBillingSystem.chargeCustomerInCents(customerId, amountInCents);
    }
}