import java.util.Map;

public class PinAuthenticator {
    private final Map<String, Integer> pins = Map.of(
            "4000123412341234", 1234,
            "5555444433331111", 4321);

    public boolean authenticate(String cardNumber, int pin) {
        return pins.containsKey(cardNumber) && pins.get(cardNumber) == pin;
    }
}
