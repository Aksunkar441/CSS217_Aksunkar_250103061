import java.util.Set;

public class EligibilityChecker {
    private final Set<String> eligibleStudents = Set.of("STU-100", "STU-200", "STU-300", "STU-400");

    public boolean isEligible(String studentId) {
        boolean eligible = eligibleStudents.contains(studentId);
        System.out.println(eligible ? "Student eligibility verified." : "Registration declined: student is not eligible.");
        return eligible;
    }
}
