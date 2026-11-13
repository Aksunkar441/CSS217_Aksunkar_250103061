public class BadgeGenerator {
    private int nextBadgeNumber = 1001;

    public String generate(String studentId, String track) {
        String badge = "EVT-" + nextBadgeNumber++ + "-" + track.toUpperCase();
        System.out.println("Event badge generated for " + studentId + ": " + badge);
        return badge;
    }
}
