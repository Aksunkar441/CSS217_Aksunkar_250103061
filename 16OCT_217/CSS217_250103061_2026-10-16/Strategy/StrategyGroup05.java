import java.util.Arrays;

class Task25_GrayscaleFilter {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface PixelFilterStrategy {
        int[] applyFilter(int[] pixels);
    }
    static class InvertStrategy implements PixelFilterStrategy {
        public int[] applyFilter(int[] p){
            int[] o = p.clone();
            for (int i = 0; i < o.length; i++)o[i] = 255 - o[i];
            return o;
        }
    }
    static class ThresholdStrategy implements PixelFilterStrategy {
        public int[] applyFilter(int[] p){
            int[] o = p.clone();
            for (int i = 0; i < o.length; i++)o[i] = o[i] >= 128 ? 255 : 0;
            return o;
        }
    }
    static class BrightenStrategy implements PixelFilterStrategy {
        public int[] applyFilter(int[] p){
            int[] o = p.clone();
            for (int i = 0; i < o.length; i++)o[i] = Math.min(255, o[i] + 30);
            return o;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(25, "Pixel filter", Arrays.toString(new BrightenStrategy().applyFilter(new int[]{20, 128, 240})));
    }
}

class Task28_LoanInterest {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface InterestStrategy {
        double computeInterest(double principal, int years);
    }
    static class SimpleInterest implements InterestStrategy {
        public double computeInterest(double p, int y){
            return p * .08 * y;
        }
    }
    static class CompoundInterest implements InterestStrategy {
        public double computeInterest(double p, int y){
            return p * Math.pow(1.08, y) - p;
        }
    }
    static class PromoInterest implements InterestStrategy {
        public double computeInterest(double p, int y){
            return y == 1 ? 0 : p * .12 * y;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(28, "Compound interest", new CompoundInterest().computeInterest(1000, 3));
    }
}
