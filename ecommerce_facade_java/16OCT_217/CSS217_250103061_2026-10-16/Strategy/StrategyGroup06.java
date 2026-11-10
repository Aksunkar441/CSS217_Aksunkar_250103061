
class Task31_CaseConverter {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface CaseStrategy {
        String convert(String phrase);
    }
    static class SnakeCaseStrategy implements CaseStrategy {
        public String convert(String s){
            return String.join("_", s.trim().split("\\s+"));
        }
    }
    static class KebabCaseStrategy implements CaseStrategy {
        public String convert(String s){
            return String.join("-", s.trim().split("\\s+"));
        }
    }
    static class PascalCaseStrategy implements CaseStrategy {
        public String convert(String s){
            StringBuilder b = new StringBuilder();
            for (String w : s.trim().split("\\s+"))if (!w.isEmpty())b.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
            return b.toString();
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(31, "Identifier conversion", new PascalCaseStrategy().convert("user login count"));
    }
}

class Task34_CpuScheduler {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface SchedulerStrategy {
        int selectNextIndex(int[] burstTimes);
    }
    static class ShortestJobFirst implements SchedulerStrategy {
        public int selectNextIndex(int[] a){
            if (a.length == 0)return - 1;
            int m = 0;
            for (int i = 1; i < a.length; i++)if (a[i] < a[m])m = i;
            return m;
        }
    }
    static class LongestJobFirst implements SchedulerStrategy {
        public int selectNextIndex(int[] a){
            if (a.length == 0)return - 1;
            int m = 0;
            for (int i = 1; i < a.length; i++)if (a[i] > a[m])m = i;
            return m;
        }
    }
    static class FirstComeFirstServed implements SchedulerStrategy {
        public int selectNextIndex(int[] a){
            return a.length == 0 ? - 1 : 0;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(34, "CPU scheduler", new ShortestJobFirst().selectNextIndex(new int[]{9, 3, 6}));
    }
}
