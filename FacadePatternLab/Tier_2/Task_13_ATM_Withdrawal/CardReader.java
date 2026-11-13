import java.util.Set;

public class CardReader {
    private final Set<String> knownCards = Set.of("4000123412341234", "5555444433331111");

    public boolean readCard(String cardNumber) {
        return cardNumber != null && knownCards.contains(cardNumber);
    }
}
