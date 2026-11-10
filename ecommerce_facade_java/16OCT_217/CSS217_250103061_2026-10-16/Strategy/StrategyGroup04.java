
class Task19_IncomeTax {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface TaxStrategy {
        double calculateTax(double income);
    }
    static class ZeroTax implements TaxStrategy {
        public double calculateTax(double i){
            return 0;
        }
    }
    static class FlatTax implements TaxStrategy {
        public double calculateTax(double i){
            return i * .10;
        }
    }
    static class ProgressiveTax implements TaxStrategy {
        public double calculateTax(double i){
            return Math.min(i, 100000) * .05 + Math.max(0, i - 100000) * .20;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(19, "Progressive tax", new ProgressiveTax().calculateTax(150000));
    }
}

class Task22_RouteTime {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface RouteStrategy {
        int estimateMinutes(int distanceKm);
    }
    static class WalkingStrategy implements RouteStrategy {
        public int estimateMinutes(int d){
            return d * 12;
        }
    }
    static class CyclingStrategy implements RouteStrategy {
        public int estimateMinutes(int d){
            return d * 4;
        }
    }
    static class DrivingStrategy implements RouteStrategy {
        public int estimateMinutes(int d){
            return d + 10;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(22, "Route estimate", new DrivingStrategy().estimateMinutes(20) + " min");
    }
}
