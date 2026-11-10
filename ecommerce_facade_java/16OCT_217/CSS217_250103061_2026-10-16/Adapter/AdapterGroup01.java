
class Task03_TemperatureSensor {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface CelsiusThermometer {
        double getTemperatureC();
    }
    static class FahrenheitSensor {
        double readFahrenheit() {
            return 98.6;
        }
    }
    static class SensorAdapter implements CelsiusThermometer {
        private final FahrenheitSensor sensor;
        SensorAdapter(FahrenheitSensor s) {
            sensor = s;
        }
        public double getTemperatureC() {
            return (sensor.readFahrenheit() - 32) * 5.0 / 9.0;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(3, "Fahrenheit adapter", new SensorAdapter(new FahrenheitSensor()).getTemperatureC() + " C");
    }
}

class Task06_DotMatrixPrinter {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface ModernPrinter {
        void printDocument(String text);
    }
    static class DotMatrixPrinter {
        void printCharArray(char[] b){
            for (int i = 0; i < b.length; i++) System.out.print((i == 0 ? "": " ") + b[i]);
            System.out.println();
        }
    }
    static class PrinterAdapter implements ModernPrinter {
        private final DotMatrixPrinter printer;
        PrinterAdapter(DotMatrixPrinter p){
            printer = p;
        }
        public void printDocument(String s){
            printer.printCharArray(s.toCharArray());
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(6, "Dot-matrix adapter", "printed below:");
        new PrinterAdapter(new DotMatrixPrinter()).printDocument("LAB");
    }
}
