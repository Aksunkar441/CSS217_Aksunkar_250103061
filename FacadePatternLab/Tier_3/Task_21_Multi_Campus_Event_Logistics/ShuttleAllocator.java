import java.util.HashMap;
import java.util.Map;

public class ShuttleAllocator {
    private final Map<String, Integer> roundTripSeats = new HashMap<>();

    public ShuttleAllocator() {
        roundTripSeats.put(route("Almaty", "Astana"), 1);
        roundTripSeats.put(route("Astana", "Almaty"), 1);
    }

    private String route(String homeCampus, String eventCampus) {
        return homeCampus.toUpperCase() + "->" + eventCampus.toUpperCase();
    }

    public boolean reserveRoundTrip(String homeCampus, String eventCampus) {
        String route = route(homeCampus, eventCampus);
        int available = roundTripSeats.getOrDefault(route, 0);
        if (available == 0) {
            System.out.println("Shuttle reservation declined: no round-trip seats are available.");
            return false;
        }
        roundTripSeats.put(route, available - 1);
        System.out.println("Round-trip shuttle seat reserved.");
        return true;
    }

    public void releaseRoundTrip(String homeCampus, String eventCampus) {
        String route = route(homeCampus, eventCampus);
        roundTripSeats.put(route, roundTripSeats.getOrDefault(route, 0) + 1);
        System.out.println("Round-trip shuttle reservation released.");
    }
}
