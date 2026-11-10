package Task19Adapter;

import java.util.HashMap;
import java.util.Map;

public class CachedDatabaseAdapter implements ICachedDataService {
    private final ExpensiveRemoteDatabase database;
    private final Map<Integer, String> cache = new HashMap<>();
    private int hitCounter;

    public CachedDatabaseAdapter(ExpensiveRemoteDatabase database) { this.database = database; }
    public CachedDatabaseAdapter() { this(new ExpensiveRemoteDatabase()); }

    @Override
    public String read(int id) {
        if (cache.containsKey(id)) {
            hitCounter++;
            return cache.get(id);
        }
        String value = database.queryById(id);
        cache.put(id, value);
        return value;
    }

    @Override public int getCacheHitCount() { return hitCounter; }
}
