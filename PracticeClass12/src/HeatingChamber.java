// Subsystem 3: Controls the gas burner, airflow damper, and chamber temperature.
public class HeatingChamber {
    // Tracks whether the main gas burner is currently lit.
    private boolean burnerIgnited = false;
    // Ambient room temperature in Celsius when idle.
    private int currentTempCelsius = 25;

    // Ignites the burner. Requires a spinning drum so beans do not burn on contact.
    public void igniteBurner(boolean drumIsSpinning) {
        if (!drumIsSpinning) {
            // Critical hardware interlock: never ignite heat inside a stationary drum.
            throw new IllegalStateException("SAFETY FAULT: Cannot ignite burner while drum is stationary!");
        }
        this.burnerIgnited = true;
        System.out.println("[HeatingChamber] Gas burner IGNITED.");
    }

    // Ramps chamber temperature up to the target roast profile temperature.
    public void heatToTarget(int targetTempCelsius) {
        if (!burnerIgnited) {
            // Precondition: burner must be lit before raising temperature.
            throw new IllegalStateException("Cannot heat: burner is not ignited.");
        }
        if (targetTempCelsius < 160 || targetTempCelsius > 245) {
            // Below 160C will not develop beans; above 245C risks drum fire.
            throw new IllegalArgumentException("Target roast temperature must be between 160C and 245C.");
        }
        this.currentTempCelsius = targetTempCelsius;
        System.out.println("[HeatingChamber] Chamber stabilized at " + currentTempCelsius + "°C.");
    }

    // Shuts off the gas valve immediately and vents residual heat.
    public void extinguishBurner() {
        this.burnerIgnited = false;
        this.currentTempCelsius = 40; // Residual warm air after venting
        System.out.println("[HeatingChamber] Burner EXTINGUISHED. Venting residual heat.");
    }

    public int getCurrentTempCelsius() {
        return currentTempCelsius;
    }

    public boolean isBurnerIgnited() {
        return burnerIgnited;
    }
}
