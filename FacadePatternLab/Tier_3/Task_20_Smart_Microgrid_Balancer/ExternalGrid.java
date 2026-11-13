public class ExternalGrid {
    private final double importCapKw;

    public ExternalGrid(double importCapKw) {
        if (importCapKw < 0) throw new IllegalArgumentException("Grid cap cannot be negative.");
        this.importCapKw = importCapKw;
    }

    public double importPower(double requestedKw) {
        double imported = Math.min(requestedKw, importCapKw);
        System.out.printf("Imported %.2f kW from the external grid (cap %.2f kW).%n",
                imported, importCapKw);
        return imported;
    }
}
