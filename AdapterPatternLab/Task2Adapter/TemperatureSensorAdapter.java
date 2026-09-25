package Task2Adapter;

public class TemperatureSensorAdapter implements ICelsiusSensor {

    private final FahrenheitSensor sensor;

    public TemperatureSensorAdapter(FahrenheitSensor sensor) {
        this.sensor = sensor;
    }

    public TemperatureSensorAdapter() {
        this(new FahrenheitSensor());
    }

    @Override
    public double getTemperatureInCelsius() {

        String raw = sensor.readRawTemperature();
        String cleaned = raw.trim();
        if (cleaned.endsWith("F")) {
            cleaned = cleaned.substring(0, cleaned.length() - 1).trim();
        }
        double fahrenheit = Double.parseDouble(cleaned);

        double celsius = (fahrenheit - 32) * (5.0 / 9.0);

        // 6. Округляем до 2 знаков после запятой
        return Math.round(celsius * 100.0) / 100.0;
    }
}