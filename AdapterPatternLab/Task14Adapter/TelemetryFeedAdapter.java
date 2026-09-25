package Task14Adapter;

import java.util.LinkedHashMap;
import java.util.Map;

public class TelemetryFeedAdapter implements ITelemetryService {
    private final LegacySensorFeed feed;

    public TelemetryFeedAdapter(LegacySensorFeed feed) { this.feed = feed; }
    public TelemetryFeedAdapter() { this(new LegacySensorFeed()); }

    @Override
    public Map<String, String> getCleanTelemetry() {
        Map<String, String> result = new LinkedHashMap<>();
        String raw = feed.getRawTelemetry();
        if (raw == null) return result;
        for (String token : raw.split(";")) {
            String pair = token.trim();
            int separator = pair.indexOf('=');
            if (separator < 0) continue;
            String key = pair.substring(0, separator).trim();
            String value = pair.substring(separator + 1).trim();
            if (!key.isEmpty()) result.put(key, value);
        }
        return result;
    }
}
