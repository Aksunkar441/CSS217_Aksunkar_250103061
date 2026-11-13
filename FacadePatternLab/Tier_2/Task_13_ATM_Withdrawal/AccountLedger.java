import java.util.HashMap;
import java.util.Map;

public class AccountLedger {
    private final Map<String, Double> balances = new HashMap<>();

    public AccountLedger() {
        balances.put("4000123412341234", 650.00);
        balances.put("5555444433331111", 90.00);
    }

    public boolean hasFunds(String cardNumber, double amount) {
        return balances.getOrDefault(cardNumber, 0.0) >= amount;
    }

    public boolean debit(String cardNumber, double amount) {
        if (!hasFunds(cardNumber, amount)) return false;
        balances.put(cardNumber, balances.get(cardNumber) - amount);
        return true;
    }

    public double getBalance(String cardNumber) {
        return balances.getOrDefault(cardNumber, 0.0);
    }
}
