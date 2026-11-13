import java.util.Map;
import java.util.Set;

public class WorkshopScheduler {
    private final Map<String, Set<String>> tracksByCampus = Map.of(
            "ASTANA", Set.of("AI", "ROBOTICS"),
            "ALMATY", Set.of("AI")
    );

    public boolean isScheduled(String eventCampus, String track) {
        boolean scheduled = tracksByCampus
                .getOrDefault(eventCampus.toUpperCase(), Set.of())
                .contains(track.toUpperCase());
        if (scheduled) {
            System.out.println("Workshop track and campus schedule confirmed.");
        } else {
            System.out.println("Registration declined: this track is not scheduled at that campus.");
        }
        return scheduled;
    }
}
