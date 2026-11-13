import java.util.Objects;

// Coffee recipes are coordinated here, not in the individual hardware classes.
public class CoffeeMachineFacade {
    private final WaterHeater heater;
    private final CoffeeGrinder grinder;
    private final BrewUnit brewUnit;
    private final CupDispenser cupDispenser;

    public CoffeeMachineFacade(WaterHeater heater, CoffeeGrinder grinder,
                               BrewUnit brewUnit, CupDispenser cupDispenser) {
        this.heater = Objects.requireNonNull(heater);
        this.grinder = Objects.requireNonNull(grinder);
        this.brewUnit = Objects.requireNonNull(brewUnit);
        this.cupDispenser = Objects.requireNonNull(cupDispenser);
    }

    public void brewEspresso() {
        System.out.println("\nMaking espresso.");
        cupDispenser.dispenseCup();
        heater.heatWater(92);
        grinder.grindBeans("fine");
        brewUnit.brew(25);
    }

    public void brewAmericano() {
        System.out.println("\nMaking americano.");
        cupDispenser.dispenseCup();
        heater.heatWater(95);
        grinder.grindBeans("medium");
        brewUnit.brew(40);
        heater.dispenseHotWater(120);
    }
}
