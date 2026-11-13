public class Main {
    public static void main(String[] args) {
        GymOnboardingFacade gym = new GymOnboardingFacade(
                new ProfileRegistry(), new HealthScreening(),
                new LockerAssigner(), new AccessCardIssuer());

        System.out.println(gym.registerNewMember("Jordan", 19, true));
        System.out.println(gym.registerNewMember("Casey", 15, true));
        System.out.println(gym.registerNewMember("Taylor", 22, false));
    }
}
