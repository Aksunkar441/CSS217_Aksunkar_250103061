public class ThermostatAdapter implements SmartDevice {
    private final LegacyThermostat thermostat;

    public ThermostatAdapter(LegacyThermostat thermostat) {
        if (thermostat == null) {
            throw new IllegalArgumentException("Thermostat cannot be null");
        }

        this.thermostat = thermostat;
    }

    public void turnOn() {
        if ("IDLE".equals(thermostat.checkDial())) {
            thermostat.rotateDial("LOW");
        }
    }

    public void turnOff() {
        thermostat.rotateDial("IDLE");
    }

    public boolean isOn() {
        String state = thermostat.checkDial();

        return "LOW".equals(state) || "MEDIUM".equals(state) || "MAX".equals(state);
    }

    public int getPowerPercent() {
        String state = thermostat.checkDial();

        if ("IDLE".equals(state)) {
            return 0;
        }

        if ("LOW".equals(state)) {
            return 33;
        }

        if ("MEDIUM".equals(state)) {
            return 66;
        }

        if ("MAX".equals(state)) {
            return 100;
        }

        return -1;
    }
}