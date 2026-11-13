public class Main {
    public static void main(String[] args) {
        CoffeeMachineFacade machine = new CoffeeMachineFacade(
                new WaterHeater(), new CoffeeGrinder(), new BrewUnit(), new CupDispenser());
        machine.brewEspresso();
        machine.brewAmericano();
    }
}
