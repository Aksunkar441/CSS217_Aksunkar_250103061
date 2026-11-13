public class PharmacyDispenser {
    public String preorderStarterMedication(TriagePriority priority) {
        String order = switch (priority) {
            case CRITICAL -> "Emergency starter medication kit";
            case URGENT -> "Urgent assessment medication pack";
            case STANDARD -> "Standard admission medication pack";
        };
        System.out.println("Pharmacy pre-order placed: " + order + ".");
        return order;
    }
}
