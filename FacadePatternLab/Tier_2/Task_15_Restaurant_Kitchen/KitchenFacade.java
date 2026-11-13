import java.util.Objects;

public class KitchenFacade {
    private final IngredientStock ingredientStock;
    private final GrillStation grill;
    private final SaladStation saladStation;
    private final PlatingCounter plating;
    private final WaiterCallSystem waiterCall;

    public KitchenFacade(IngredientStock ingredientStock, GrillStation grill,
                         SaladStation saladStation, PlatingCounter plating,
                         WaiterCallSystem waiterCall) {
        this.ingredientStock = Objects.requireNonNull(ingredientStock);
        this.grill = Objects.requireNonNull(grill);
        this.saladStation = Objects.requireNonNull(saladStation);
        this.plating = Objects.requireNonNull(plating);
        this.waiterCall = Objects.requireNonNull(waiterCall);
    }

    public boolean prepareComboMeal(String mainDish, String sideSalad, int tableNumber) {
        if (tableNumber <= 0) {
            System.out.println("Order rejected: table number must be positive.");
            return false;
        }
        if (!ingredientStock.hasIngredients(mainDish, sideSalad)) {
            System.out.println("Order rejected: unknown menu item or insufficient ingredients.");
            return false;
        }
        ingredientStock.reserveIngredients(mainDish, sideSalad);
        grill.prepareMainDish(mainDish);
        saladStation.prepareSalad(sideSalad);
        plating.plateCombo(tableNumber);
        waiterCall.notifyServer(tableNumber);
        return true;
    }
}
