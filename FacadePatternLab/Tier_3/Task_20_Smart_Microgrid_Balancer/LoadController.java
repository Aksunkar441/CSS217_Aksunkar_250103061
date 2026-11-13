public class LoadController {
    private final double maxShedKw;

    public LoadController(double maxShedKw) {
        if (maxShedKw < 0) throw new IllegalArgumentException("Sheddable load cannot be negative.");
        this.maxShedKw = maxShedKw;
    }

    public double shedNonEssentialCircuits(double deficitKw) {
        double shed = Math.min(Math.max(0, deficitKw), maxShedKw);
        System.out.printf("Load controller shed %.2f kW of non-essential circuits.%n", shed);
        return shed;
    }
}
