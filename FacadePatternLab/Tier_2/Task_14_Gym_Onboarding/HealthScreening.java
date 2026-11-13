public class HealthScreening {
    public String validate(int age, boolean medicalCleared) {
        if (age < 16) return "Member must be at least 16 years old.";
        if (!medicalCleared) return "Medical clearance is required before registration.";
        return null;
    }
}
