import java.util.Objects;

public class WashingMachineFacade {
    private final DoorSensor doorSensor;
    private final WaterValve waterValve;
    private final DetergentDispenser detergent;
    private final DrumMotor drumMotor;
    private final DrainPump drainPump;

    public WashingMachineFacade(DoorSensor doorSensor, WaterValve waterValve,
                                DetergentDispenser detergent, DrumMotor drumMotor,
                                DrainPump drainPump) {
        this.doorSensor = Objects.requireNonNull(doorSensor);
        this.waterValve = Objects.requireNonNull(waterValve);
        this.detergent = Objects.requireNonNull(detergent);
        this.drumMotor = Objects.requireNonNull(drumMotor);
        this.drainPump = Objects.requireNonNull(drainPump);
    }

    public void runQuickWash() {
        if (!doorSensor.isClosed()) {
            System.out.println("Quick wash cancelled: washer door is open.");
            return;
        }
        System.out.println("\nStarting quick wash.");
        waterValve.fillWithColdWater();
        waterValve.close();
        detergent.dispenseNormalAmount();
        drumMotor.spin(15, "gentle");
        drainPump.drain();
        System.out.println("Quick wash complete.");
    }

    public void runHeavyWash() {
        if (!doorSensor.isClosed()) {
            System.out.println("Heavy wash cancelled: washer door is open.");
            return;
        }
        System.out.println("\nStarting heavy wash.");
        waterValve.fillWithWarmWater();
        waterValve.close();
        detergent.dispenseExtraDetergent();
        drumMotor.spin(45, "high");
        drainPump.drain();
        System.out.println("Heavy wash complete.");
    }
}
