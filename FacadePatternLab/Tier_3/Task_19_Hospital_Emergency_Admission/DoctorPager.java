public class DoctorPager {
    public String pageOnCallSpecialist(TriagePriority priority) {
        String specialist = switch (priority) {
            case CRITICAL -> "Emergency medicine specialist";
            case URGENT -> "Internal medicine specialist";
            case STANDARD -> "General practitioner";
        };
        System.out.println("Paged on-call: " + specialist + ".");
        return specialist;
    }
}
