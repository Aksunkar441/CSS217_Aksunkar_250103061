import java.util.LinkedHashMap;
import java.util.Map;

public class RoomAvailability {
    private final Map<String, Integer> rates = Map.of(
            "SINGLE", 80, "DOUBLE", 120, "SUITE", 220);
    private final Map<Integer, String> openRooms = new LinkedHashMap<>();
    private final Map<Integer, String> occupiedRooms = new LinkedHashMap<>();

    public RoomAvailability() {
        openRooms.put(101, "SINGLE");
        openRooms.put(102, "SINGLE");
        openRooms.put(201, "DOUBLE");
        openRooms.put(301, "SUITE");
    }

    public int findAvailableRoom(String roomType) {
        for (Map.Entry<Integer, String> room : openRooms.entrySet()) {
            if (room.getValue().equals(roomType)) return room.getKey();
        }
        return -1;
    }

    public int getNightlyRate(String roomType) {
        if (roomType == null) return -1;
        return rates.getOrDefault(roomType, -1);
    }

    public boolean assignRoom(int roomNumber, String guestName) {
        if (!openRooms.containsKey(roomNumber)) return false;
        openRooms.remove(roomNumber);
        occupiedRooms.put(roomNumber, guestName);
        return true;
    }

    public boolean releaseRoom(int roomNumber) {
        String guest = occupiedRooms.remove(roomNumber);
        if (guest == null) return false;
        String roomType = roomNumber < 200 ? "SINGLE" : roomNumber < 300 ? "DOUBLE" : "SUITE";
        openRooms.put(roomNumber, roomType);
        return true;
    }

    public boolean isOccupied(int roomNumber) {
        return occupiedRooms.containsKey(roomNumber);
    }
}
