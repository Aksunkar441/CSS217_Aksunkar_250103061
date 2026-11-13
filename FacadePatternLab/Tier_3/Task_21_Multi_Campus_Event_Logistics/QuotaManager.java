import java.util.HashMap;
import java.util.Map;

public class QuotaManager {
    private final Map<String, Integer> remainingSeats = new HashMap<>();

    public QuotaManager() {
        remainingSeats.put(key("Astana", "AI"), 3);
        remainingSeats.put(key("Almaty", "AI"), 2);
        remainingSeats.put(key("Astana", "Robotics"), 2);
    }

    private String key(String campus, String track) {
        return campus.toUpperCase() + "|" + track.toUpperCase();
    }

    public boolean reserveSeat(String eventCampus, String track) {
        String key = key(eventCampus, track);
        int available = remainingSeats.getOrDefault(key, 0);
        if (available == 0) {
            System.out.println("No workshop seats remain for this campus and track.");
            return false;
        }
        remainingSeats.put(key, available - 1);
        System.out.println("Workshop seat reserved. Seats left: " + (available - 1));
        return true;
    }

    public void releaseSeat(String eventCampus, String track) {
        String key = key(eventCampus, track);
        remainingSeats.put(key, remainingSeats.getOrDefault(key, 0) + 1);
        System.out.println("Workshop reservation released.");
    }

    public int remainingSeats(String eventCampus, String track) {
        return remainingSeats.getOrDefault(key(eventCampus, track), 0);
    }
}
