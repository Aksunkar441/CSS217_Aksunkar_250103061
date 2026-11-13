import java.util.HashMap;
import java.util.Map;

public class FineCalculator {
    private final Map<String, Double> unpaidFines = new HashMap<>();

    public FineCalculator() {
        unpaidFines.put("S1002", 5.50);
        unpaidFines.put("S1003", 0.0);
    }

    public double getUnpaidFine(String studentId) {
        return unpaidFines.getOrDefault(studentId, 0.0);
    }
}
