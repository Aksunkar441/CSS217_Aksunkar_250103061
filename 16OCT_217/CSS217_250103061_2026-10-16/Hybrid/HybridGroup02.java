import java.util.Locale;

class Task50_WeatherAlert {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface CelsiusSensor {
        double getCelsius();
    }
    static class KelvinSensor {
        private final double k;
        KelvinSensor(double k){
            this.k = k;
        }
        double readKelvin(){
            return k;
        }
    }
    static class KelvinAdapter implements CelsiusSensor {
        private final KelvinSensor s;
        KelvinAdapter(KelvinSensor s){
            this.s = s;
        }
        public double getCelsius(){
            return s.readKelvin() - 273.15;
        }
    }
    interface AlertStrategy {
        boolean isTriggered(double tempC);
    }
    static class HeatwaveAlert implements AlertStrategy {
        public boolean isTriggered(double t){
            return t > 40;
        }
    }
    static class FrostAlert implements AlertStrategy {
        public boolean isTriggered(double t){
            return t < 0;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        double temp = new KelvinAdapter(new KelvinSensor(268.15)).getCelsius();
        show(50, "Weather alert hybrid", temp + " C, frost="+ new FrostAlert().isTriggered(temp));
    }
}
