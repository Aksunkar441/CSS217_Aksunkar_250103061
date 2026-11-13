public record AdmissionRecord(String patientName, TriagePriority priority,
                              String bedType, String warning,
                              String medicationOrder, String pagedSpecialist) {
    public void printSummary() {
        System.out.println("\nAdmission: " + patientName);
        System.out.println("Priority: " + priority);
        System.out.println("Bed: " + bedType);
        if (!warning.isBlank()) System.out.println(warning);
        System.out.println("Pharmacy: " + medicationOrder);
        System.out.println("On-call: " + pagedSpecialist);
    }
}
