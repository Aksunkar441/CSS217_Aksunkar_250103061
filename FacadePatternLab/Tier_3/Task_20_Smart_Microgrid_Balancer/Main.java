public class Main {
    public static void main(String[] args) {
        BatteryBank battery = new BatteryBank(20.0, 12.0, 20.0, 8.0);
        MicrogridFacade microgrid = new MicrogridFacade(
                new SolarPanelArray(6.0), battery, new ExternalGrid(5.0),
                new LoadController(4.0), new TelemetryLogger());

        // Available supply is not enough for this peak, so non-essential load is shed.
        microgrid.balancePower(26.0).printSummary();
        // The next dispatch shows that the battery will not cross its 20% reserve.
        microgrid.balancePower(8.0).printSummary();
        System.out.printf("Reserve floor: %.2f kWh; current charge: %.2f kWh%n",
                battery.getReserveKwh(), battery.getStateOfChargeKwh());
    }
}
