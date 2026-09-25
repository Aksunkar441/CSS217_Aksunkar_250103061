package Task6Adapter;

public class DatabaseAdapter implements IRepository {
    private final LegacyDatabaseConnection database;

    public DatabaseAdapter(LegacyDatabaseConnection database) { this.database = database; }
    public DatabaseAdapter() { this(new LegacyDatabaseConnection()); }

    @Override
    public String findById(int id) throws RecordNotFoundException, DatabaseLockedException {
        String[] outBuffer = new String[1];
        int result = database.executeFetch(id, outBuffer);
        if (result == -1) throw new RecordNotFoundException("Record not found: " + id);
        if (result == -2) throw new DatabaseLockedException("Database is locked while fetching record: " + id);
        if (result != 0) throw new IllegalStateException("Unexpected database result: " + result);
        return outBuffer[0];
    }
}
