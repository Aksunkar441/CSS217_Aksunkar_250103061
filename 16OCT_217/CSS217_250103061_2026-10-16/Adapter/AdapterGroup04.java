
class Task21_CsvExporter {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface CsvExporter {
        String exportCsv();
    }
    static class ArrayDataStore {
        String[] fetchWords(){
            return new String[]{"patterns", "in", "Java"};
        }
    }
    static class ArrayToCsvAdapter implements CsvExporter {
        private final ArrayDataStore store;
        ArrayToCsvAdapter(ArrayDataStore s){
            store = s;
        }
        public String exportCsv(){
            return String.join(",", store.fetchWords());
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(21, "CSV adapter", new ArrayToCsvAdapter(new ArrayDataStore()).exportCsv());
    }
}

class Task24_ConsoleLogger {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface AppLogger {
        void log(String level, String message);
    }
    static class ConsoleWriter {
        void writeLine(String s){
            System.out.print(s);
        }
    }
    static class LoggerAdapter implements AppLogger {
        private final ConsoleWriter writer;
        LoggerAdapter(ConsoleWriter w){
            writer = w;
        }
        public void log(String l, String m){
            writer.writeLine("[" + l.toUpperCase() + "] "+ m);
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(24, "Logger adapter", "logging below:");
        new LoggerAdapter(new ConsoleWriter()).log("info", "ready");
        System.out.println();
    }
}
