public class SolarPanelArray {
    private final double generationKw;

    public SolarPanelArray(double generationKw) {
        if (generationKw < 0) throw new IllegalArgumentException("Solar generation cannot be negative.");
        this.generationKw = generationKw;
    }

    public double getGenerationKw() { return generationKw; }
}
