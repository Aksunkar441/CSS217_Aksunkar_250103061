import java.util.Objects;

// The facade checks the battery before asking the starter to crank the engine.
public class CarIgnitionFacade {
    private final Battery battery;
    private final FuelPump fuelPump;
    private final StarterMotor starter;
    private final DashboardDisplay dashboard;
    private boolean engineRunning;

    public CarIgnitionFacade(Battery battery, FuelPump fuelPump,
                             StarterMotor starter, DashboardDisplay dashboard) {
        this.battery = Objects.requireNonNull(battery);
        this.fuelPump = Objects.requireNonNull(fuelPump);
        this.starter = Objects.requireNonNull(starter);
        this.dashboard = Objects.requireNonNull(dashboard);
    }

    public void startEngine() {
        if (engineRunning) {
            System.out.println("Engine is already running.");
            return;
        }
        System.out.println("\nStarting car.");
        dashboard.turnOnGauges();
        if (battery.checkVoltage() < 12.0) {
            System.out.println("Battery voltage is too low to start the engine.");
            dashboard.turnOffGauges();
            return;
        }
        fuelPump.prime();
        engineRunning = starter.crank();
    }

    public void stopEngine() {
        if (!engineRunning) {
            System.out.println("Engine is already stopped.");
            return;
        }
        System.out.println("\nStopping car.");
        starter.stop();
        fuelPump.stop();
        dashboard.turnOffGauges();
        engineRunning = false;
    }
}
