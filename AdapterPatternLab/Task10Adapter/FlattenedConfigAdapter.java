package Task10Adapter;

import java.util.Map;

public class FlattenedConfigAdapter implements IConfiguration {
    private final NestedConfigStore store;

    public FlattenedConfigAdapter(NestedConfigStore store) { this.store = store; }
    public FlattenedConfigAdapter() { this(new NestedConfigStore()); }

    @Override
    public String getString(String dottedKey) {
        if (dottedKey == null || dottedKey.isEmpty()) return null;
        Object current = store.getRawConfig();
        for (String segment : dottedKey.split("\\.")) {
            if (!(current instanceof Map<?, ?> map) || !map.containsKey(segment)) return null;
            current = map.get(segment);
        }
        return current == null ? null : String.valueOf(current);
    }
}
