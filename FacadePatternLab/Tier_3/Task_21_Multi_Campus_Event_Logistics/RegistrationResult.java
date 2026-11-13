public record RegistrationResult(boolean success, String message,
                                 String badgeId, boolean shuttleReserved) {
    public void printResult() {
        System.out.println("\nRegistration: " + (success ? "CONFIRMED" : "DECLINED"));
        System.out.println(message);
        if (badgeId != null) System.out.println("Badge: " + badgeId);
        System.out.println("Shuttle reserved: " + shuttleReserved);
    }
}
