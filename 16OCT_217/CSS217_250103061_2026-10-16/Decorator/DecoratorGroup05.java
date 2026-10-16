import java.util.Locale;

class Task26_CarCustomizer {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface Car {
        String getConfiguration();
        double getPrice();
    }
    static class BaseSedan implements Car {
        public String getConfiguration(){
            return "Standard Sedan";
        }
        public double getPrice(){
            return 15000;
        }
    }
    abstract static class CarDecorator implements Car {
        final Car inner;
        CarDecorator(Car c){
            inner = c;
        }
    }
    static class SunroofDecorator extends CarDecorator {
        SunroofDecorator(Car c){
            super(c);
        }
        public String getConfiguration(){
            return inner.getConfiguration() + ", Sunroof";
        }
        public double getPrice(){
            return inner.getPrice() + 1200;
        }
    }
    static class AlloyWheelsDecorator extends CarDecorator {
        AlloyWheelsDecorator(Car c){
            super(c);
        }
        public String getConfiguration(){
            return inner.getConfiguration() + ", Alloy Wheels";
        }
        public double getPrice(){
            return inner.getPrice() + 800;
        }
    }
    static class DashcamDecorator extends CarDecorator {
        DashcamDecorator(Car c){
            super(c);
        }
        public String getConfiguration(){
            return inner.getConfiguration() + ", Dashcam";
        }
        public double getPrice(){
            return inner.getPrice() + 300;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Car car = new DashcamDecorator(new AlloyWheelsDecorator(new SunroofDecorator(new BaseSedan())));
        show(26, "Car configuration", car.getConfiguration() + " / "+ car.getPrice());
    }
}

class Task29_RequestMiddleware {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface RequestHandler {
        String handle(String request);
    }
    static class CoreHandler implements RequestHandler {
        public String handle(String r){
            return "200 OK: "+ r;
        }
    }
    abstract static class HandlerDecorator implements RequestHandler {
        final RequestHandler inner;
        HandlerDecorator(RequestHandler h){
            inner = h;
        }
    }
    static class AuthDecorator extends HandlerDecorator {
        AuthDecorator(RequestHandler h){
            super(h);
        }
        public String handle(String r){
            return r.startsWith("AUTH_") ? inner.handle(r.substring(5)) : "403 Forbidden";
        }
    }
    static class LengthLimitDecorator extends HandlerDecorator {
        LengthLimitDecorator(RequestHandler h){
            super(h);
        }
        public String handle(String r){
            return r.length() > 30 ? "413 Payload Too Large": inner.handle(r);
        }
    }
    static class LogDecorator extends HandlerDecorator {
        LogDecorator(RequestHandler h){
            super(h);
        }
        public String handle(String r){
            return inner.handle(r) + " [LOGGED]";
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        RequestHandler handler = new LogDecorator(new LengthLimitDecorator(new AuthDecorator(new CoreHandler())));
        show(29, "Request middleware", handler.handle("AUTH_get-status"));
    }
}
