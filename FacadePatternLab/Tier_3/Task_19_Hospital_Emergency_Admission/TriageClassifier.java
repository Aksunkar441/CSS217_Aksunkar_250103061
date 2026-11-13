public class TriageClassifier {
    // These thresholds are illustrative rules for the classroom simulation, not clinical guidance.
    public TriagePriority classify(PatientVitals vitals) {
        int heartRate = vitals.heartRate();
        int systolicBp = vitals.systolicBp();
        double temp = vitals.temperatureC();

        if (heartRate < 40 || heartRate > 130 || systolicBp < 90
                || temp >= 40.0 || temp < 35.0) {
            return TriagePriority.CRITICAL;
        }
        if (heartRate < 50 || heartRate > 110 || systolicBp < 100
                || temp >= 38.5 || temp < 35.5) {
            return TriagePriority.URGENT;
        }
        return TriagePriority.STANDARD;
    }
}
