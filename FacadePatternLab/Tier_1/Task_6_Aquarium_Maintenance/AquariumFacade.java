import java.util.Objects;

// The facade groups regular tank care into morning and night routines.
public class AquariumFacade {
    private final WaterFilter filter;
    private final AquariumHeater heater;
    private final AutoFeeder feeder;
    private final LedHoodLight light;

    public AquariumFacade(WaterFilter filter, AquariumHeater heater,
                          AutoFeeder feeder, LedHoodLight light) {
        this.filter = Objects.requireNonNull(filter);
        this.heater = Objects.requireNonNull(heater);
        this.feeder = Objects.requireNonNull(feeder);
        this.light = Objects.requireNonNull(light);
    }

    public void runMorningRoutine() {
        System.out.println("\nMorning aquarium routine.");
        filter.cycle();
        heater.setTemperature(25);
        feeder.dispenseFood("one small pinch");
        light.setDaylightMode();
    }

    public void prepareForNight() {
        System.out.println("\nPreparing aquarium for night.");
        filter.cycle();
        light.setMoonlightMode();
    }
}
