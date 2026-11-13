import java.util.Objects;

// Dispatch order is deliberate: solar first, then battery above its protected reserve,
// and finally the capped utility connection.
public class MicrogridFacade {
    private final SolarPanelArray solar;
    private final BatteryBank battery;
    private final ExternalGrid grid;
    private final LoadController loads;
    private final TelemetryLogger telemetry;

    public MicrogridFacade(SolarPanelArray solar, BatteryBank battery, ExternalGrid grid,
                           LoadController loads, TelemetryLogger telemetry) {
        this.solar = Objects.requireNonNull(solar);
        this.battery = Objects.requireNonNull(battery);
        this.grid = Objects.requireNonNull(grid);
        this.loads = Objects.requireNonNull(loads);
        this.telemetry = Objects.requireNonNull(telemetry);
    }

    public EnergySummary balancePower(double demandKw) {
        if (!Double.isFinite(demandKw) || demandKw < 0) {
            throw new IllegalArgumentException("Demand must be a finite, non-negative value.");
        }
        System.out.printf("\nBalancing %.2f kW of demand.%n", demandKw);
        double remaining = demandKw;

        double solarUsed = Math.min(remaining, solar.getGenerationKw());
        remaining -= solarUsed;

        double batteryUsed = battery.dischargeForOneHour(remaining);
        remaining -= batteryUsed;

        double gridUsed = grid.importPower(remaining);
        remaining -= gridUsed;

        double shed = 0;
        if (remaining > 0.000001) {
            telemetry.log(String.format("Power deficit detected: %.2f kW before load shedding.", remaining));
            shed = loads.shedNonEssentialCircuits(remaining);
            remaining -= shed;
            telemetry.log(String.format("Load-shedding event: %.2f kW curtailed.", shed));
        }

        EnergySummary summary = new EnergySummary(demandKw, solarUsed, batteryUsed,
                gridUsed, shed, Math.max(0, remaining), battery.getStateOfChargeKwh());
        telemetry.log(String.format("Dispatch completed; unmet demand %.2f kW.", summary.unmetDemandKw()));
        return summary;
    }
}
