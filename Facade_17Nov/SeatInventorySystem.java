// Subsystem 1: Manages aircraft seat availability and temporary locks.
public class SeatInventorySystem {
    private boolean connected = false;
    // Must be called before checking or locking any seat.
    public void connectToReservationServer() {
        this.connected = true;
        System.out.println("[SeatInventory] Connected to central seat server.");
    }
    // Verifies whether a seat code (e.g., "12A") is valid and open.
    public boolean isSeatAvailable(String flightCode, String seatNumber) {
        if (!connected) {
            throw new IllegalStateException("SeatInventorySystem is not connected.");
        }
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            return false;
        }
    // Simulated rule: Row 13 seats are blocked for crew use.
        return !seatNumber.toUpperCase().startsWith("13");
    }
    // Locks the seat for the passenger and returns a lock token.
    public String lockSeat(String flightCode, String seatNumber, String passengerName) {
        if (!isSeatAvailable(flightCode, seatNumber)) {
            throw new IllegalArgumentException("Seat " + seatNumber + " cannot be locked.");
        }
        String lockToken = "LOCK-" + flightCode.toUpperCase() + "-" + seatNumber.toUpperCase();
        System.out.println("[SeatInventory] Locked seat " + seatNumber + " for " + passengerName +
                " (" + lockToken + ")");
        return lockToken;
    }
    // Releases a previously acquired lock if booking fails later in the pipeline.
    public void releaseSeatLock(String lockToken) {
        if (connected && lockToken != null) {
            System.out.println("[SeatInventory] Released lock: " + lockToken);
        }
    }
    public void disconnect() {
        this.connected = false;
        System.out.println("[SeatInventory] Disconnected from seat server.");
    }
}