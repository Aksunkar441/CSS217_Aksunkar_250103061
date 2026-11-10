
class Task32_InvoiceStacker {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface Invoice {
        double getTotal();
    }
    static class BaseInvoice implements Invoice {
        private final double subtotal;
        BaseInvoice(double s){
            subtotal = s;
        }
        public double getTotal(){
            return subtotal;
        }
    }
    abstract static class InvoiceDecorator implements Invoice {
        final Invoice inner;
        InvoiceDecorator(Invoice i){
            inner = i;
        }
    }
    static class TaxDecorator extends InvoiceDecorator {
        TaxDecorator(Invoice i){
            super(i);
        }
        public double getTotal(){
            return inner.getTotal() * 1.18;
        }
    }
    static class PackagingFeeDecorator extends InvoiceDecorator {
        PackagingFeeDecorator(Invoice i){
            super(i);
        }
        public double getTotal(){
            return inner.getTotal() + 45;
        }
    }
    static class VipDiscountDecorator extends InvoiceDecorator {
        VipDiscountDecorator(Invoice i){
            super(i);
        }
        public double getTotal(){
            return inner.getTotal() * .90;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Invoice invoice = new VipDiscountDecorator(new PackagingFeeDecorator(new TaxDecorator(new BaseInvoice(100))));
        show(32, "Invoice decorators", invoice.getTotal());
    }
}

class Task35_IceCreamCalories {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface Dessert {
        String describe();
        int getCalories();
    }
    static class VanillaScoop implements Dessert {
        public String describe(){
            return "Vanilla Scoop";
        }
        public int getCalories(){
            return 150;
        }
    }
    abstract static class DessertDecorator implements Dessert {
        final Dessert inner;
        DessertDecorator(Dessert d){
            inner = d;
        }
    }
    static class HotFudgeDecorator extends DessertDecorator {
        HotFudgeDecorator(Dessert d){
            super(d);
        }
        public String describe(){
            return inner.describe() + ", Hot Fudge";
        }
        public int getCalories(){
            return inner.getCalories() + 120;
        }
    }
    static class SprinklesDecorator extends DessertDecorator {
        SprinklesDecorator(Dessert d){
            super(d);
        }
        public String describe(){
            return inner.describe() + ", Sprinkles";
        }
        public int getCalories(){
            return inner.getCalories() + 45;
        }
    }
    static class WaffleConeDecorator extends DessertDecorator {
        WaffleConeDecorator(Dessert d){
            super(d);
        }
        public String describe(){
            return inner.describe() + ", Waffle Cone";
        }
        public int getCalories(){
            return inner.getCalories() + 110;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Dessert dessert = new WaffleConeDecorator(new SprinklesDecorator(new HotFudgeDecorator(new VanillaScoop())));
        show(35, "Ice cream calories", dessert.describe() + " / "+ dessert.getCalories());
    }
}
