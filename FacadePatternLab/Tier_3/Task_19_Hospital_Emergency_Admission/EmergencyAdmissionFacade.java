import java.util.Objects;

// The facade turns vitals into a priority, then coordinates the resources for admission.
public class EmergencyAdmissionFacade {
    private final VitalsMonitor monitor;
    private final TriageClassifier classifier;
    private final BedAllocator beds;
    private final PharmacyDispenser pharmacy;
    private final DoctorPager pager;

    public EmergencyAdmissionFacade(VitalsMonitor monitor, TriageClassifier classifier,
                                    BedAllocator beds, PharmacyDispenser pharmacy,
                                    DoctorPager pager) {
        this.monitor = Objects.requireNonNull(monitor);
        this.classifier = Objects.requireNonNull(classifier);
        this.beds = Objects.requireNonNull(beds);
        this.pharmacy = Objects.requireNonNull(pharmacy);
        this.pager = Objects.requireNonNull(pager);
    }

    public AdmissionRecord admitPatient(String patientName, int heartRate,
                                        int systolicBp, double tempC) {
        Objects.requireNonNull(patientName, "patientName");
        PatientVitals vitals = monitor.capture(heartRate, systolicBp, tempC);
        TriagePriority priority = classifier.classify(vitals);
        System.out.println("Triage priority: " + priority + ".");

        BedAssignment bed = switch (priority) {
            case CRITICAL -> beds.allocateIcu();
            case URGENT -> beds.allocateMonitoredEr();
            case STANDARD -> beds.allocateGeneral();
        };
        String medication = pharmacy.preorderStarterMedication(priority);
        String specialist = pager.pageOnCallSpecialist(priority);
        return new AdmissionRecord(patientName, priority, bed.bedType(), bed.warning(),
                medication, specialist);
    }
}
