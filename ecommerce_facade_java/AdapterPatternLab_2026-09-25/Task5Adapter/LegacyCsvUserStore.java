package Task5Adapter;

public class LegacyCsvUserStore {
    // Returns raw CSV lines formatted as: "id, name, role"
    public String fetchNextRow() {
        return "101, John Doe, ADMIN";
    }
}
