import java.util.Set;

public class MemberValidator {
    private final Set<String> activeStudentIds = Set.of("S1001", "S1002", "S1003");

    public boolean isActiveMember(String studentId) {
        return activeStudentIds.contains(studentId);
    }
}
