import java.util.Locale;

class Task08_PizzaToppings {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface Pizza {
        String getDetails();
        int getPrice();
    }
    static class Margherita implements Pizza {
        public String getDetails(){
            return "Margherita";
        }
        public int getPrice(){
            return 200;
        }
    }
    abstract static class PizzaDecorator implements Pizza {
        final Pizza inner;
        PizzaDecorator(Pizza p){
            inner = p;
        }
    }
    static class ExtraCheese extends PizzaDecorator {
        ExtraCheese(Pizza p){
            super(p);
        }
        public String getDetails(){
            return inner.getDetails() + ", Extra Cheese";
        }
        public int getPrice(){
            return inner.getPrice() + 40;
        }
    }
    static class Olives extends PizzaDecorator {
        Olives(Pizza p){
            super(p);
        }
        public String getDetails(){
            return inner.getDetails() + ", Olives";
        }
        public int getPrice(){
            return inner.getPrice() + 30;
        }
    }
    static class Jalapenos extends PizzaDecorator {
        Jalapenos(Pizza p){
            super(p);
        }
        public String getDetails(){
            return inner.getDetails() + ", Jalapenos";
        }
        public int getPrice(){
            return inner.getPrice() + 25;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Pizza pizza = new Jalapenos(new Olives(new ExtraCheese(new Margherita())));
        show(8, "Pizza toppings", pizza.getDetails() + " / "+ pizza.getPrice());
    }
}

class Task11_Notifications {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface Notifier {
        String send(String msg);
    }
    static class EmailNotifier implements Notifier {
        public String send(String m){
            return "EMAIL: "+ m;
        }
    }
    abstract static class NotifierDecorator implements Notifier {
        final Notifier inner;
        NotifierDecorator(Notifier n){
            inner = n;
        }
    }
    static class SMSDecorator extends NotifierDecorator {
        SMSDecorator(Notifier n){
            super(n);
        }
        public String send(String m){
            return inner.send(m) + " | SMS: "+ m;
        }
    }
    static class SlackDecorator extends NotifierDecorator {
        SlackDecorator(Notifier n){
            super(n);
        }
        public String send(String m){
            return inner.send(m) + " | SLACK: "+ m;
        }
    }
    static class WhatsAppDecorator extends NotifierDecorator {
        WhatsAppDecorator(Notifier n){
            super(n);
        }
        public String send(String m){
            return inner.send(m) + " | WA: "+ m;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Notifier notifier = new WhatsAppDecorator(new SlackDecorator(new SMSDecorator(new EmailNotifier())));
        show(11, "Multi-channel notification", notifier.send("Hello"));
    }
}
