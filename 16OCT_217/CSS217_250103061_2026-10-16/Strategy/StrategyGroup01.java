import java.util.Arrays;

class Task01_DiscountEngine {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface DiscountStrategy {
        double applyDiscount(double price);
    }
    static class FlatDiscount implements DiscountStrategy {
        public double applyDiscount(double p) {
            return Math.max(0, p - 50);
        }
    }
    static class PercentageDiscount implements DiscountStrategy {
        public double applyDiscount(double p) {
            return p * .85;
        }
    }
    static class SeasonalDiscount implements DiscountStrategy {
        public double applyDiscount(double p) {
            return p * (p > 500 ? .75 : .95);
        }
    }
    static class ShoppingCart {
        private DiscountStrategy strategy;
        ShoppingCart(DiscountStrategy s) {
            strategy = s;
        }
        void setStrategy(DiscountStrategy s) {
            strategy = s;
        }
        double checkout(double total) {
            return strategy.applyDiscount(total);
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(new FlatDiscount());
        show(1, "Discount checkout", cart.checkout(120));
    }
}

class Task04_ArraySorting {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface SortStrategy {
        void sort(int[] data);
    }
    static class BubbleSortStrategy implements SortStrategy {
        public void sort(int[] a) {
            for (int i = 0; i < a.length; i++) for (int j = 1; j < a.length - i; j++) if (a[j - 1] > a[j]) {
                int t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
        }
    }
    static class SelectionSortStrategy implements SortStrategy {
        public void sort(int[] a) {
            for (int i = 0; i < a.length; i++){
                int m = i;
                for (int j = i + 1; j < a.length; j++)if (a[j] < a[m])m = j;
                int t = a[i];
                a[i] = a[m];
                a[m] = t;
            }
        }
    }
    static class InsertionSortStrategy implements SortStrategy {
        public void sort(int[] a) {
            for (int i = 1; i < a.length; i++){
                int v = a[i], j = i - 1;
                while (j >= 0 && a[j] > v){
                    a[j + 1] = a[j--];
                }
                a[j + 1] = v;
            }
        }
    }
    static class DataProcessor {
        private SortStrategy strategy;
        DataProcessor(SortStrategy s){
            strategy = s;
        }
        void setStrategy(SortStrategy s){
            strategy = s;
        }
        int[] process(int[] a){
            strategy.sort(a);
            return a;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        DataProcessor dp = new DataProcessor(new BubbleSortStrategy());
        show(4, "Sorting strategy", Arrays.toString(dp.process(new int[]{5, 2, 4, 1})));
    }
}
