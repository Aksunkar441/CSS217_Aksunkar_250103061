import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryService() {
        stock.put("ITEM-100", 3);
        stock.put("ITEM-200", 1);
    }

    public boolean reserveStock(String itemId) {
        int available = stock.getOrDefault(itemId, 0);
        if (available <= 0) {
            System.out.println("No stock available for " + itemId + ".");
            return false;
        }
        stock.put(itemId, available - 1);
        System.out.println("Reserved one unit of " + itemId + ".");
        return true;
    }

    public void releaseStock(String itemId) {
        stock.put(itemId, stock.getOrDefault(itemId, 0) + 1);
        System.out.println("Stock reservation released.");
    }
}
