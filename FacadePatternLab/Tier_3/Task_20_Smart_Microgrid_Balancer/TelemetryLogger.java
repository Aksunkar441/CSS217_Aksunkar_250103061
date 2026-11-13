import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TelemetryLogger {
    private final List<String> events = new ArrayList<>();

    public void log(String message) {
        events.add(message);
        System.out.println("Telemetry: " + message);
    }

    public List<String> getEvents() { return Collections.unmodifiableList(events); }
}
