
class Task37_GradingScale {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface GradingStrategy {
        String evaluate(int score);
    }
    static class LetterGradeStrategy implements GradingStrategy {
        public String evaluate(int s){
            return s >= 90 ? "A": s >= 75 ? "B": s >= 50 ? "C": "F";
        }
    }
    static class PassFailStrategy implements GradingStrategy {
        public String evaluate(int s){
            return s >= 50 ? "PASS": "FAIL";
        }
    }
    static class HonorsStrategy implements GradingStrategy {
        public String evaluate(int s){
            return s >= 85 ? "DISTINCTION": s >= 60 ? "MERIT": "UNGRADED";
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(37, "Grading strategy", new HonorsStrategy().evaluate(88));
    }
}

class Task40_ArraySearch {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface SearchStrategy {
        int findIndex(int[] sortedArr, int target);
    }
    static class LinearSearchLeft implements SearchStrategy {
        public int findIndex(int[] a, int t){
            for (int i = 0; i < a.length; i++)if (a[i] == t)return i;
            return - 1;
        }
    }
    static class LinearSearchRight implements SearchStrategy {
        public int findIndex(int[] a, int t){
            for (int i = a.length - 1; i >= 0; i--)if (a[i] == t)return i;
            return - 1;
        }
    }
    static class BinarySearchStrategy implements SearchStrategy {
        public int findIndex(int[] a, int t){
            int l = 0, r = a.length - 1;
            while (l <= r){
                int m = l + (r - l) / 2;
                if (a[m] == t)return m;
                if (a[m] < t)l = m + 1;
                else r = m - 1;
            }
            return - 1;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(40, "Array search", new BinarySearchStrategy().findIndex(new int[]{1, 3, 5, 7}, 5));
    }
}
