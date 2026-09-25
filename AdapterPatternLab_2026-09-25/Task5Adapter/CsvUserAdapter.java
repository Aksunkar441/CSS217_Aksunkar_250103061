package Task5Adapter;

public class CsvUserAdapter implements IUserSource {
    private final LegacyCsvUserStore store;

    public CsvUserAdapter(LegacyCsvUserStore store) {
        this.store = store;
    }

    public CsvUserAdapter() {
        this(new LegacyCsvUserStore());
    }

    @Override
    public UserProfile getNextUser() {
        String line = store.fetchNextRow();
        if (line == null) {
            throw new IllegalStateException("User store returned no row");
        }

        String[] fields = line.split(",", -1);
        if (fields.length != 3) {
            throw new IllegalStateException("Invalid user row: " + line);
        }
        try {
            return new UserProfile(Integer.parseInt(fields[0].trim()), fields[1].trim(), fields[2].trim());
        } catch (NumberFormatException exception) {
            throw new IllegalStateException("Invalid user id in row: " + line, exception);
        }
    }
}
