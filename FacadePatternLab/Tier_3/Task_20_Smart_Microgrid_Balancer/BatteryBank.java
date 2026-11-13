public class BatteryBank {
    private final double capacityKwh;
    private final double minimumReservePercent;
    private final double maxDischargeKw;
    private double stateOfChargeKwh;

    public BatteryBank(double capacityKwh, double stateOfChargeKwh,
                       double minimumReservePercent, double maxDischargeKw) {
        if (capacityKwh <= 0 || stateOfChargeKwh < 0 || stateOfChargeKwh > capacityKwh
                || minimumReservePercent < 0 || minimumReservePercent >= 100 || maxDischargeKw < 0) {
            throw new IllegalArgumentException("Invalid battery configuration.");
        }
        this.capacityKwh = capacityKwh;
        this.stateOfChargeKwh = stateOfChargeKwh;
        this.minimumReservePercent = minimumReservePercent;
        this.maxDischargeKw = maxDischargeKw;
    }

    // For this lab each balancing call represents a one-hour dispatch, so kW equals kWh used.
    public double dischargeForOneHour(double requestedKw) {
        double reserveKwh = capacityKwh * minimumReservePercent / 100.0;
        double availableKwh = Math.max(0, stateOfChargeKwh - reserveKwh);
        double suppliedKw = Math.min(requestedKw, Math.min(maxDischargeKw, availableKwh));
        stateOfChargeKwh -= suppliedKw;
        System.out.printf("Battery supplied %.2f kW; reserve remains %.2f kWh.%n",
                suppliedKw, stateOfChargeKwh);
        return suppliedKw;
    }

    public double getStateOfChargeKwh() { return stateOfChargeKwh; }
    public double getReserveKwh() { return capacityKwh * minimumReservePercent / 100.0; }
}
