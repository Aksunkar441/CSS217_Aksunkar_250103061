public class Main {
    public static void main(String[] args) {
        KitchenFacade kitchen = new KitchenFacade(new IngredientStock(), new GrillStation(),
                new SaladStation(), new PlatingCounter(), new WaiterCallSystem());

        kitchen.prepareComboMeal("BURGER", "GARDEN SALAD", 12);
        kitchen.prepareComboMeal("BURGER", "GARDEN SALAD", 8);
        kitchen.prepareComboMeal("BURGER", "GARDEN SALAD", 4);
    }
}
