import java.util.HashSet;
import java.util.Set;

public class LoanRecorder {
    private final Set<String> activeLoans = new HashSet<>();

    private String loanKey(String studentId, String isbn) {
        return studentId + ":" + isbn;
    }

    public boolean recordLoan(String studentId, String isbn) {
        return activeLoans.add(loanKey(studentId, isbn));
    }

    public boolean hasLoan(String studentId, String isbn) {
        return activeLoans.contains(loanKey(studentId, isbn));
    }

    public boolean closeLoan(String studentId, String isbn) {
        return activeLoans.remove(loanKey(studentId, isbn));
    }
}
