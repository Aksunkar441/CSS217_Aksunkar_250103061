public class Main {
    public static void main(String[] args) {
        EmergencyAdmissionFacade admissions = new EmergencyAdmissionFacade(
                new VitalsMonitor(), new TriageClassifier(), new BedAllocator(),
                new PharmacyDispenser(), new DoctorPager());

        admissions.admitPatient("Alex Morgan", 145, 82, 39.4).printSummary();
        // The only ICU bed is now occupied, so this critical patient uses the monitored ER fallback.
        admissions.admitPatient("Sam Lee", 138, 88, 39.0).printSummary();
        admissions.admitPatient("Jordan Kim", 84, 122, 36.8).printSummary();
    }
}
