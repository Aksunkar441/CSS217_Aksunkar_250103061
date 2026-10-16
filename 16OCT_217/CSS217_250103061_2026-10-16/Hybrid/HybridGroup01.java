import java.util.Locale;

class Task49_ReceiptGenerator {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface ReceiptTaxStrategy {
        double calculate(double price);
    }
    static class LocalTax implements ReceiptTaxStrategy {
        public double calculate(double p){
            return p * 1.05;
        }
    }
    static class LuxuryTax implements ReceiptTaxStrategy {
        public double calculate(double p){
            return p * 1.18;
        }
    }
    interface Receipt {
        String render();
    }
    static class BaseReceipt implements Receipt {
        private final double finalPrice;
        BaseReceipt(double price, ReceiptTaxStrategy tax){
            finalPrice = tax.calculate(price);
        }
        public String render(){
            return String.format(Locale.ROOT, "Total: %.2f", finalPrice);
        }
    }
    abstract static class ReceiptDecorator implements Receipt {
        final Receipt inner;
        ReceiptDecorator(Receipt r){
            inner = r;
        }
    }
    static class ReceiptHeaderDecorator extends ReceiptDecorator {
        ReceiptHeaderDecorator(Receipt r){
            super(r);
        }
        public String render(){
            return "=== STORE ===\n"+ inner.render();
        }
    }
    static class PromoFooterDecorator extends ReceiptDecorator {
        PromoFooterDecorator(Receipt r){
            super(r);
        }
        public String render(){
            return inner.render() + "\nUSE CODE: SAVE10";
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Receipt receipt = new PromoFooterDecorator(new ReceiptHeaderDecorator(new BaseReceipt(100, new LocalTax())));
        show(49, "Taxed receipt", receipt.render().replace("\n", " | "));
    }
}
