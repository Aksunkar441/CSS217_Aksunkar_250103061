public class DoorSensor {
    private boolean closed = true;

    public boolean isClosed() { return closed; }
    public void openDoor() { closed = false; }
    public void closeDoor() { closed = true; }
}
