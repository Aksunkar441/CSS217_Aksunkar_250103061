import java.util.HashMap;
import java.util.Map;

public class IngredientStock {
    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, Map<String, Integer>> recipes = new HashMap<>();

    public IngredientStock() {
        stock.put("beef patty", 2);
        stock.put("chicken breast", 3);
        stock.put("lettuce", 4);
        stock.put("tomato", 4);
        stock.put("cucumber", 2);

        recipes.put("BURGER", Map.of("beef patty", 1));
        recipes.put("GRILLED CHICKEN", Map.of("chicken breast", 1));
        recipes.put("GARDEN SALAD", Map.of("lettuce", 1, "tomato", 1));
        recipes.put("CUCUMBER SALAD", Map.of("cucumber", 1, "tomato", 1));
    }

    public boolean hasIngredients(String mainDish, String sideSalad) {
        Map<String, Integer> required = combine(mainDish, sideSalad);
        if (required == null) return false;
        for (Map.Entry<String, Integer> item : required.entrySet()) {
            if (stock.getOrDefault(item.getKey(), 0) < item.getValue()) return false;
        }
        return true;
    }

    public boolean reserveIngredients(String mainDish, String sideSalad) {
        Map<String, Integer> required = combine(mainDish, sideSalad);
        if (required == null || !hasIngredients(mainDish, sideSalad)) return false;
        for (Map.Entry<String, Integer> item : required.entrySet()) {
            stock.put(item.getKey(), stock.get(item.getKey()) - item.getValue());
        }
        return true;
    }

    private Map<String, Integer> combine(String mainDish, String sideSalad) {
        if (mainDish == null || sideSalad == null) return null;
        Map<String, Integer> main = recipes.get(mainDish.toUpperCase());
        Map<String, Integer> salad = recipes.get(sideSalad.toUpperCase());
        if (main == null || salad == null || !sideSalad.toUpperCase().contains("SALAD")) return null;
        Map<String, Integer> result = new HashMap<>(main);
        salad.forEach((ingredient, count) -> result.merge(ingredient, count, Integer::sum));
        return result;
    }
}
