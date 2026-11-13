public class VitalsMonitor {
    public PatientVitals capture(int heartRate, int systolicBp, double tempC) {
        PatientVitals vitals = new PatientVitals(heartRate, systolicBp, tempC);
        System.out.printf("Vitals recorded: HR %d bpm, BP %d mmHg, temperature %.1f°C.%n",
                heartRate, systolicBp, tempC);
        return vitals;
    }
}
