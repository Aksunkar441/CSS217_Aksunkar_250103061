
class Task38_TokenGenerator {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface TokenGenerator {
        String generate(String seed);
    }
    static class BasicToken implements TokenGenerator {
        public String generate(String s){
            return s;
        }
    }
    abstract static class TokenDecorator implements TokenGenerator {
        final TokenGenerator inner;
        TokenDecorator(TokenGenerator t){
            inner = t;
        }
    }
    static class SaltDecorator extends TokenDecorator {
        SaltDecorator(TokenGenerator t){
            super(t);
        }
        public String generate(String s){
            return inner.generate(s) + "_SALT99";
        }
    }
    static class HexLengthDecorator extends TokenDecorator {
        HexLengthDecorator(TokenGenerator t){
            super(t);
        }
        public String generate(String s){
            String v = inner.generate(s);
            return v + "_"+ Integer.toHexString(v.length());
        }
    }
    static class DuplicateDecorator extends TokenDecorator {
        DuplicateDecorator(TokenGenerator t){
            super(t);
        }
        public String generate(String s){
            String v = inner.generate(s);
            return v + ":"+ v;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        TokenGenerator token = new DuplicateDecorator(new HexLengthDecorator(new SaltDecorator(new BasicToken())));
        show(38, "Token decorators", token.generate("seed"));
    }
}

class Task41_BurgerKiosk {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface Burger {
        String getIngredients();
        double getPrice();
    }
    static class VegPattyBurger implements Burger {
        public String getIngredients(){
            return "Bun + Veg Patty";
        }
        public double getPrice(){
            return 80;
        }
    }
    abstract static class BurgerDecorator implements Burger {
        final Burger inner;
        BurgerDecorator(Burger b){
            inner = b;
        }
    }
    static class CheeseSliceDecorator extends BurgerDecorator {
        CheeseSliceDecorator(Burger b){
            super(b);
        }
        public String getIngredients(){
            return inner.getIngredients() + " + Cheese";
        }
        public double getPrice(){
            return inner.getPrice() + 20;
        }
    }
    static class DoublePattyDecorator extends BurgerDecorator {
        DoublePattyDecorator(Burger b){
            super(b);
        }
        public String getIngredients(){
            return inner.getIngredients() + " + Extra Patty";
        }
        public double getPrice(){
            return inner.getPrice() + 50;
        }
    }
    static class SpicyMayoDecorator extends BurgerDecorator {
        SpicyMayoDecorator(Burger b){
            super(b);
        }
        public String getIngredients(){
            return inner.getIngredients() + " + Spicy Mayo";
        }
        public double getPrice(){
            return inner.getPrice() + 15;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Burger burger = new SpicyMayoDecorator(new DoublePattyDecorator(new CheeseSliceDecorator(new VegPattyBurger())));
        show(41, "Burger kiosk", burger.getIngredients() + " / "+ burger.getPrice());
    }
}
