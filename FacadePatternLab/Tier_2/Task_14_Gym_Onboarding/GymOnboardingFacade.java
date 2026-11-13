import java.util.Objects;

public class GymOnboardingFacade {
    private final ProfileRegistry profiles;
    private final HealthScreening screening;
    private final LockerAssigner lockers;
    private final AccessCardIssuer cardIssuer;

    public GymOnboardingFacade(ProfileRegistry profiles, HealthScreening screening,
                               LockerAssigner lockers, AccessCardIssuer cardIssuer) {
        this.profiles = Objects.requireNonNull(profiles);
        this.screening = Objects.requireNonNull(screening);
        this.lockers = Objects.requireNonNull(lockers);
        this.cardIssuer = Objects.requireNonNull(cardIssuer);
    }

    public String registerNewMember(String name, int age, boolean medicalCleared) {
        if (name == null || name.isBlank()) return "Registration failed: name is required.";
        String rejection = screening.validate(age, medicalCleared);
        if (rejection != null) return "Registration failed: " + rejection;

        String memberId = profiles.createProfile(name, age);
        int lockerNumber = lockers.assignLocker();
        String accessCard = cardIssuer.issueCard();
        return "Welcome to the gym, " + name + "!\n"
                + "Member ID: " + memberId + "\n"
                + "Locker: " + lockerNumber + "\n"
                + "Access card: " + accessCard;
    }
}
