public class BillingService {
    public boolean chargeStay(String guestName, int nights, int nightlyRate) {
        if (nights <= 0 || nightlyRate <= 0) return false;
        int total = nights * nightlyRate;
        System.out.println(guestName + " charged $" + total + " for " + nights + " night(s).");
        return true;
    }
}
