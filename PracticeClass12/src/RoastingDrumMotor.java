// Subsystem 2: Drives the rotating perforated steel drum that tumbles the beans.
public class RoastingDrumMotor {
    // Current rotational speed in Revolutions Per Minute (0 = stopped).
    private int currentRpm = 0;

    // Starts the drum motor at the requested RPM (valid operating range: 30 to 65 RPM).
    public void startRotation(int targetRpm) {
        if (targetRpm < 30 || targetRpm > 65) {
            // Too slow causes scorching; too fast pins beans against drum walls via centrifugal force.
            throw new IllegalArgumentException("Drum RPM must be between 30 and 65.");
        }
        this.currentRpm = targetRpm;
        System.out.println("[DrumMotor] Drum rotating at " + currentRpm + " RPM.");
    }

    // Adjusts speed mid-roast (for example, slowing down during second crack).
    public void adjustRpm(int newRpm) {
        if (currentRpm == 0) {
            // Precondition: motor must already be running before adjusting speed.
            throw new IllegalStateException("Cannot adjust RPM while drum motor is stopped.");
        }
        startRotation(newRpm);
    }

    // Brings the roasting drum to a complete halt.
    public void stopRotation() {
        this.currentRpm = 0;
        System.out.println("[DrumMotor] Drum rotation STOPPED (0 RPM).");
    }

    // Used to verify the drum is spinning before igniting the gas burner.
    public boolean isSpinning() {
        return currentRpm > 0;
    }
}
