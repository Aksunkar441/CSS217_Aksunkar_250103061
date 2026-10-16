import java.util.Locale;

class Task02_CoffeeBilling {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface Beverage {
        String getDescription();
        double getCost();
    }
    static class Espresso implements Beverage {
        public String getDescription() {
            return "Espresso";
        }
        public double getCost() {
            return 100;
        }
    }
    abstract static class BeverageDecorator implements Beverage {
        final Beverage inner;
        BeverageDecorator(Beverage b) {
            inner = b;
        }
    }
    static class MilkDecorator extends BeverageDecorator {
        MilkDecorator(Beverage b) {
            super(b);
        }
        public String getDescription() {
            return inner.getDescription() + ", Milk";
        }
        public double getCost() {
            return inner.getCost() + 20;
        }
    }
    static class CaramelDecorator extends BeverageDecorator {
        CaramelDecorator(Beverage b) {
            super(b);
        }
        public String getDescription() {
            return inner.getDescription() + ", Caramel";
        }
        public double getCost() {
            return inner.getCost() + 35;
        }
    }
    static class WhippedCreamDecorator extends BeverageDecorator {
        WhippedCreamDecorator(Beverage b) {
            super(b);
        }
        public String getDescription() {
            return inner.getDescription() + ", Whipped Cream";
        }
        public double getCost() {
            return inner.getCost() + 25;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Beverage coffee = new WhippedCreamDecorator(new CaramelDecorator(new MilkDecorator(new Espresso())));
        show(2, "Coffee bill", coffee.getDescription() + " / "+ coffee.getCost());
    }
}

class Task05_TextFormatter {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface TextComponent {
        String format(String input);
    }
    static class PlainText implements TextComponent {
        public String format(String s){
            return s;
        }
    }
    abstract static class TextDecorator implements TextComponent {
        final TextComponent inner;
        TextDecorator(TextComponent c){
            inner = c;
        }
    }
    static class UpperCaseDecorator extends TextDecorator {
        UpperCaseDecorator(TextComponent c){
            super(c);
        }
        public String format(String s){
            return inner.format(s).toUpperCase();
        }
    }
    static class TrimDecorator extends TextDecorator {
        TrimDecorator(TextComponent c){
            super(c);
        }
        public String format(String s){
            return inner.format(s).trim();
        }
    }
    static class BracketDecorator extends TextDecorator {
        BracketDecorator(TextComponent c){
            super(c);
        }
        public String format(String s){
            return "["+ inner.format(s) + "]";
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        TextComponent fmt = new BracketDecorator(new UpperCaseDecorator(new TrimDecorator(new PlainText())));
        show(5, "Text pipeline", fmt.format("  hello Java  "));
    }
}
