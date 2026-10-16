import java.util.Locale;

class Task07_ShippingCost {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface ShippingStrategy {
        double calculate(double weightKg, double distanceKm);
    }
    static class StandardShipping implements ShippingStrategy {
        public double calculate(double w, double d){
            return w * 2 + d * .5;
        }
    }
    static class ExpressShipping implements ShippingStrategy {
        public double calculate(double w, double d){
            return w * 5 + d * 1.2 + 50;
        }
    }
    static class DroneShipping implements ShippingStrategy {
        public double calculate(double w, double d){
            return w < 5 ? 100 : - 1;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(7, "Shipping strategy", new ExpressShipping().calculate(3, 20));
    }
}

class Task10_PasswordValidation {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface ValidationStrategy {
        boolean isValid(String password);
    }
    static class LengthValidator implements ValidationStrategy {
        public boolean isValid(String s){
            return s != null && s.length() >= 8;
        }
    }
    static class DigitValidator implements ValidationStrategy {
        public boolean isValid(String s){
            return s != null && s.chars().anyMatch(Character::isDigit);
        }
    }
    static class NoSpaceValidator implements ValidationStrategy {
        public boolean isValid(String s){
            return s != null && s.indexOf(' ') == - 1;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(10, "Password validators", new LengthValidator().isValid("abc12345") + ", "+ new DigitValidator().isValid("abc12345") + ", "+ new NoSpaceValidator().isValid("abc12345"));
    }
}
