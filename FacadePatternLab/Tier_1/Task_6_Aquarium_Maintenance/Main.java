public class Main {
    public static void main(String[] args) {
        AquariumFacade aquarium = new AquariumFacade(
                new WaterFilter(), new AquariumHeater(), new AutoFeeder(), new LedHoodLight());
        aquarium.runMorningRoutine();
        aquarium.prepareForNight();
    }
}
