public class Main {
    public static void main(String[] args) {
        DoorSensor door = new DoorSensor();
        WashingMachineFacade washer = new WashingMachineFacade(
                door, new WaterValve(), new DetergentDispenser(),
                new DrumMotor(), new DrainPump());

        washer.runQuickWash();
        door.openDoor();
        washer.runHeavyWash();
    }
}
