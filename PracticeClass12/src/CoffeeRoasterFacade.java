// Facade: Provides a safe, high-level API over the 5 stateful roaster subsystems.
// Client code (Main.java) only ever calls methods on this class — never on the subsystems directly.
public class CoffeeRoasterFacade {

    private final BeanHopperGate    hopperGate;
    private final RoastingDrumMotor drumMotor;
    private final HeatingChamber    heatingChamber;
    private final CoolingTraySystem coolingTray;
    private final TelemetryLogger   logger;

    public CoffeeRoasterFacade() {
        this.hopperGate = new BeanHopperGate();
        this.drumMotor = new RoastingDrumMotor();
        this.heatingChamber = new HeatingChamber();
        this.coolingTray = new CoolingTraySystem();
        this.logger = new TelemetryLogger();
    }



    /**
     * Safe shutdown sequence invoked whenever a hardware check or parameter fails.
     * Guarantees: gas burner is off, hopper is closed, drum is stopped,
     * and the cooling tray is idle — regardless of how far the pipeline had progressed.
     *
     * @param reason Human-readable description of the fault that triggered the abort.
     */
    public void emergencyAbort(String reason) {
        // Log the abort event first so the telemetry trail is complete.
        logger.logState("ABORT", "Emergency shutdown triggered: " + reason);

        // 1. Kill the heat source immediately — highest priority safety action.
        heatingChamber.extinguishBurner();

        // 2. Seal the hopper gate so no beans can fall into the drum.
        hopperGate.closeGate();

        // 3. Stop the drum rotation.
        drumMotor.stopRotation();

        // 4. Close the discharge door and stop the cooling fan.
        coolingTray.finishCoolingCycle();
    }




    // ─────────────────────────────────────────────────────────────────────────
    /**
     * Executes a complete, safety-interlocked coffee roasting cycle.
     *
     * @param beanOrigin       Name/origin of the coffee beans (e.g., "Ethiopia Yirgacheffe")
     * @param batchGrams       Weight of green beans in grams (250 – 5000)
     * @param drumRpm          Drum rotation speed in RPM (30 – 65)
     * @param targetTempCelsius Peak roast chamber temperature in Celsius (160 – 245)
     * @return true  if the batch roasted and cooled cleanly;
     *         false if aborted due to a hardware fault or invalid parameter.
     */
    public boolean runRoastBatch(String beanOrigin,
                                 int batchGrams,
                                 int drumRpm,
                                 int targetTempCelsius) {
        try {
            // Step 1: Start a fresh telemetry sequence for this batch
            logger.resetBatchLog();
            logger.logState("ROASTER", "Batch started: " + beanOrigin);


            // Step 2: Stage green beans in the hopper
            hopperGate.loadGreenBeans(batchGrams);
            logger.logState("HOPPER", "Loaded " + batchGrams + "g of green beans.");


            // Step 3: Spin the drum BEFORE beans drop (interlock requirement)
            drumMotor.startRotation(drumRpm);
            logger.logState("DRUM", "Drum spinning at " + drumRpm + " RPM.");


            // Step 4: Drop beans into the drum, then seal the gate
            hopperGate.openGate();
            hopperGate.closeGate();
            logger.logState("HOPPER", "Beans charged into drum. Gate sealed.");


            // Step 5: Ignite the burner (requires drum to be spinning) ─────
            heatingChamber.igniteBurner(drumMotor.isSpinning());
            logger.logState("BURNER", "Gas burner ignited.");


            // Step 6: Ramp up to target roast temperature
            heatingChamber.heatToTarget(targetTempCelsius);
            logger.logState("ROAST", "Active roast phase at " + targetTempCelsius + "°C.");


            // Step 7: Extinguish burner — roast profile complete
            heatingChamber.extinguishBurner();
            logger.logState("BURNER", "Burner extinguished. Roast profile complete.");


            // Step 8: Cool the beans (fan first, then open door)
            coolingTray.startCoolingFan();
            // Pass current burner state — must be false to open door safely.
            coolingTray.openDischargeDoor(heatingChamber.isBurnerIgnited());
            logger.logState("COOLING", "Beans transferred to cooling tray.");


            // Step 9: Stop drum, close tray, log success
            drumMotor.stopRotation();
            coolingTray.finishCoolingCycle();
            logger.logState("ROASTER", "Batch complete: " + beanOrigin);


            return true; // Clean roast cycle finished

        } catch (RuntimeException ex) {
            // Step 10: Any hardware fault → safe shutdown, then report false
            emergencyAbort(ex.getMessage());
            return false; //  Batch aborted
        }
    }



    // ─────────────────────────────────────────────────────────────────────────
    /**
     * Preset wrapper for a bright, high-airflow light roast.
     * Fixed parameters: 55 RPM drum, 196 °C target temperature.
     */
    public boolean roastLightProfile(String beanOrigin, int batchGrams) {
        return runRoastBatch(beanOrigin, batchGrams, 55, 196);
    }

    /**
     * Preset wrapper for a rich, slower-tumbling dark roast.
     * Fixed parameters: 42 RPM drum, 230 °C target temperature.
     */
    public boolean roastDarkProfile(String beanOrigin, int batchGrams) {
        return runRoastBatch(beanOrigin, batchGrams, 42, 230);
    }
}
