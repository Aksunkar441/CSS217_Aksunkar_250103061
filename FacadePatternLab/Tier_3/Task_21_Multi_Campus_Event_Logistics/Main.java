public class Main {
    public static void main(String[] args) {
        QuotaManager quotas = new QuotaManager();
        EventLogisticsFacade events = new EventLogisticsFacade(
                new EligibilityChecker(), quotas, new WorkshopScheduler(),
                new ShuttleAllocator(), new BadgeGenerator());

        events.registerAttendee("STU-100", "Almaty", "Astana", "AI").printResult();

        int seatsBefore = quotas.remainingSeats("Astana", "AI");
        events.registerAttendee("STU-200", "Almaty", "Astana", "AI").printResult();
        int seatsAfter = quotas.remainingSeats("Astana", "AI");
        System.out.println("Astana AI seats before failed shuttle attempt: " + seatsBefore);
        System.out.println("Astana AI seats after rollback: " + seatsAfter);

        events.registerAttendee("STU-300", "Astana", "Astana", "AI").printResult();
    }
}
