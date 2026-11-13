public class ComplianceScreener {
    public boolean approve(String fromAcct, String toAcct, double amount) {
        if (fromAcct == null || toAcct == null || fromAcct.equals(toAcct)) {
            System.out.println("Compliance check rejected: account details are invalid.");
            return false;
        }
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Compliance check rejected: amount must be positive.");
            return false;
        }
        if (fromAcct.startsWith("BLOCKED") || toAcct.startsWith("BLOCKED")) {
            System.out.println("Compliance check rejected: account is on the review list.");
            return false;
        }
        System.out.println("Compliance check passed.");
        return true;
    }
}
