import java.util.Objects;

// Preset room modes prevent Main from having to call every device separately.
public class SmartRoomFacade {
    private final CeilingLight light;
    private final Thermostat thermostat;
    private final WindowBlinds blinds;
    private final DoorLock doorLock;

    public SmartRoomFacade(CeilingLight light, Thermostat thermostat,
                           WindowBlinds blinds, DoorLock doorLock) {
        this.light = Objects.requireNonNull(light);
        this.thermostat = Objects.requireNonNull(thermostat);
        this.blinds = Objects.requireNonNull(blinds);
        this.doorLock = Objects.requireNonNull(doorLock);
    }

    public void activateNightMode() {
        System.out.println("\nActivating night mode.");
        light.turnOff();
        blinds.close();
        doorLock.lock();
        thermostat.setTemperature(19);
    }

    public void activateMorningMode() {
        System.out.println("\nActivating morning mode.");
        blinds.open();
        light.turnOn();
        thermostat.setTemperature(22);
    }
}
