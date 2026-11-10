import java.util.Arrays;

class Task39_HardwareClock {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface MillisecondTimer {
        long getElapsedMillis();
    }
    static class MicrosecondClock {
        private final long micros;
        MicrosecondClock(long u){
            micros = u;
        }
        long readMicroseconds(){
            return micros;
        }
    }
    static class ClockAdapter implements MillisecondTimer {
        private final MicrosecondClock c;
        ClockAdapter(MicrosecondClock c){
            this.c = c;
        }
        public long getElapsedMillis(){
            return c.readMicroseconds() / 1000L;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(39, "Clock adapter", new ClockAdapter(new MicrosecondClock(1234567)).getElapsedMillis() + " ms");
    }
}

class Task42_GridFlattener {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface FlatDataReader {
        int[] readFlatArray();
    }
    static class GridSensor {
        private final int[][] grid;
        GridSensor(int[][] g){
            grid = g;
        }
        int[][] readGrid(){
            return grid;
        }
    }
    static class GridAdapter implements FlatDataReader {
        private final GridSensor s;
        GridAdapter(GridSensor s){
            this.s = s;
        }
        public int[] readFlatArray(){
            int[][] g = s.readGrid();
            int n = 0;
            for (int[] row : g)n += row.length;
            int[] out = new int[n];
            int k = 0;
            for (int[] row : g)for (int v : row)out[k++] = v;
            return out;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(42, "Grid adapter", Arrays.toString(new GridAdapter(new GridSensor(new int[][]{{1, 2}, {3, 4}})).readFlatArray()));
    }
}
