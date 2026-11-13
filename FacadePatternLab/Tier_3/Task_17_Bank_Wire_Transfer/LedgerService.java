import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LedgerService {
    private final List<String> entries = new ArrayList<>();

    public void record(String status, String details) {
        String entry = status + " | " + details;
        entries.add(entry);
        System.out.println("Ledger: " + entry);
    }

    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
