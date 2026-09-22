public class BulbAdapter implements SmartDevice {
    private final LegacyBulb bulb;
    //250103061
    private final int k = 1;

    public BulbAdapter(LegacyBulb bulb) {
        if (bulb == null) {
            throw new IllegalArgumentException("Bulb cannot be null");
        }
        this.bulb = bulb;
    }

    public void turnOn() {
        bulb.setBrightness(255);
    }

    public void turnOff() {
        bulb.setBrightness(0);
    }

    public boolean isOn() {
        return bulb.hasPower() && bulb.readBrightness() > 0;
    }

    public int getPowerPercent() {
        if (!bulb.hasPower()) {
            return 0;
        }

        int raw = bulb.readBrightness();

        if (raw == 0) {
            return 0;
        }

        int percent = (raw * 100) / 255 + k;

        if (percent > 100) {
            return 100;
        }

        return percent;
    }
}