public class Main {
    public static void main(String[] args) {
        SmartRoomFacade room = new SmartRoomFacade(
                new CeilingLight(), new Thermostat(), new WindowBlinds(), new DoorLock());
        room.activateNightMode();
        room.activateMorningMode();
    }
}
