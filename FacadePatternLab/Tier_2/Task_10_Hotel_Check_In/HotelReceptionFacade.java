import java.util.Objects;

public class HotelReceptionFacade {
    private final RoomAvailability rooms;
    private final BillingService billing;
    private final KeyCardEncoder keyCardEncoder;
    private final HousekeepingNotifier housekeeping;

    public HotelReceptionFacade(RoomAvailability rooms, BillingService billing,
                                KeyCardEncoder keyCardEncoder, HousekeepingNotifier housekeeping) {
        this.rooms = Objects.requireNonNull(rooms);
        this.billing = Objects.requireNonNull(billing);
        this.keyCardEncoder = Objects.requireNonNull(keyCardEncoder);
        this.housekeeping = Objects.requireNonNull(housekeeping);
    }

    public int checkInGuest(String guestName, String roomType, int nights) {
        if (guestName == null || guestName.isBlank() || nights <= 0) {
            System.out.println("Check-in rejected: provide a guest name and a positive number of nights.");
            return -1;
        }
        if (rooms.getNightlyRate(roomType) < 0) {
            System.out.println("Check-in rejected: unknown room type.");
            return -1;
        }
        int roomNumber = rooms.findAvailableRoom(roomType);
        if (roomNumber == -1) {
            System.out.println("No available rooms of type " + roomType + ".");
            return -1;
        }
        if (!billing.chargeStay(guestName, nights, rooms.getNightlyRate(roomType))) {
            System.out.println("Check-in rejected: billing could not be completed.");
            return -1;
        }
        rooms.assignRoom(roomNumber, guestName);
        keyCardEncoder.encodeKeyCard(guestName, roomNumber);
        System.out.println("Check-in complete. Assigned room " + roomNumber + ".");
        return roomNumber;
    }

    public void checkOutGuest(int roomNumber) {
        if (!rooms.releaseRoom(roomNumber)) {
            System.out.println("Check-out rejected: room " + roomNumber + " is not occupied.");
            return;
        }
        System.out.println("Guest checked out of room " + roomNumber + ".");
        housekeeping.notifyRoomReadyForCleaning(roomNumber);
    }
}
