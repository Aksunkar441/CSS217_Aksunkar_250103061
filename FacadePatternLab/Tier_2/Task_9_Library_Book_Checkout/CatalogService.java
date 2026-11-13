import java.util.HashMap;
import java.util.Map;

public class CatalogService {
    private final Map<String, Boolean> availability = new HashMap<>();

    public CatalogService() {
        availability.put("9780134685991", true);
        availability.put("9780596007126", true);
        availability.put("9780132350884", false);
    }

    public boolean isAvailable(String isbn) {
        return availability.getOrDefault(isbn, false);
    }

    public boolean checkout(String isbn) {
        if (!isAvailable(isbn)) return false;
        availability.put(isbn, false);
        return true;
    }

    public void checkIn(String isbn) {
        if (availability.containsKey(isbn)) {
            availability.put(isbn, true);
        }
    }
}
