public class BaggageHandler {
    private static final double MAX_WEIGHT_KG = 23.0;
    private int nextTag = 5001;

    public boolean isWeightAllowed(double weightKg) {
        return weightKg >= 0.0 && weightKg <= MAX_WEIGHT_KG;
    }

    public String tagBag(double weightKg) {
        String tag = "BG-" + nextTag++;
        System.out.printf("Bag tagged %s (%.1f kg).%n", tag, weightKg);
        return tag;
    }
}
