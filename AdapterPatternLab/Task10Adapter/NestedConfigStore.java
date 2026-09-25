package Task10Adapter;

import java.util.Map;

public class NestedConfigStore {
    public Map<String, Object> getRawConfig() {
        return Map.of("database", Map.of("host", "127.0.0.1", "port", 5432),
                "server", Map.of("name", "SDU-Gateway"));
    }
}
