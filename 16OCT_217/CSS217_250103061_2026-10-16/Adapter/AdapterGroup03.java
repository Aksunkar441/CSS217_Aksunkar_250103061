
class Task15_PolarCoordinates {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface CartesianPoint {
        double getX();
        double getY();
    }
    static class PolarPoint {
        private final double r, a;
        PolarPoint(double r, double a){
            this.r = r;
            this.a = a;
        }
        double getRadius(){
            return r;
        }
        double getAngleRadians(){
            return a;
        }
    }
    static class PolarToCartesianAdapter implements CartesianPoint {
        private final PolarPoint p;
        PolarToCartesianAdapter(PolarPoint p){
            this.p = p;
        }
        public double getX(){
            return p.getRadius() * Math.cos(p.getAngleRadians());
        }
        public double getY(){
            return p.getRadius() * Math.sin(p.getAngleRadians());
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        CartesianPoint point = new PolarToCartesianAdapter(new PolarPoint(2, Math.PI / 2));
        show(15, "Polar coordinate adapter", point.getX() + ", "+ point.getY());
    }
}

class Task18_CurrencyGateway {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface UsdPaymentProcessor {
        void payInUsd(double dollars);
    }
    static class InrBankGateway {
        void processRupees(double rupees){
            System.out.println("INR " + rupees);
        }
    }
    static class CurrencyAdapter implements UsdPaymentProcessor {
        private final InrBankGateway bank;
        CurrencyAdapter(InrBankGateway b){
            bank = b;
        }
        public void payInUsd(double d){
            bank.processRupees(d * 84.0);
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(18, "USD payment adapter", "paying below:");
        new CurrencyAdapter(new InrBankGateway()).payInUsd(10);
    }
}
