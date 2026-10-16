import java.util.Locale;

class Task43_Thermostat {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface CoolingStrategy {
        double getTargetTemp(int occupants);
    }
    static class EcoMode implements CoolingStrategy {
        public double getTargetTemp(int n){
            return 26;
        }
    }
    static class ComfortMode implements CoolingStrategy {
        public double getTargetTemp(int n){
            return n >= 1 ? 22 : 28;
        }
    }
    static class PartyMode implements CoolingStrategy {
        public double getTargetTemp(int n){
            return Math.max(18, 22 - n * .5);
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(43, "Thermostat strategy", new PartyMode().getTargetTemp(12) + " C");
    }
}

class Task46_SurgePricing {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface FareStrategy {
        double computeFare(double km);
    }
    static class NormalFare implements FareStrategy {
        public double computeFare(double k){
            return 50 + k * 12;
        }
    }
    static class RainSurgeFare implements FareStrategy {
        public double computeFare(double k){
            return (50 + k * 12) * 1.5;
        }
    }
    static class MidnightFare implements FareStrategy {
        public double computeFare(double k){
            return 80 + k * 15;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(46, "Ride-share fare", new RainSurgeFare().computeFare(10));
    }
}
