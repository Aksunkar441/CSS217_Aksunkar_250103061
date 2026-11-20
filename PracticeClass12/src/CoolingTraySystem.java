// Subsystem 4: Rapidly cools freshly roasted beans using a high-suction fan and agitator arm.
public class CoolingTraySystem {
    // Tracks whether the high-volume cooling exhaust fan is powered on.
    private boolean coolingFanRunning = false;
    // Tracks whether the front discharge door of the drum is open.
    private boolean dischargeDoorOpen = false;

    // Powers on the cooling tray fan and agitator paddles.
    public void startCoolingFan() {
        this.coolingFanRunning = true;
        System.out.println("[CoolingTray] Cooling exhaust fan and agitator STARTED.");
    }

    // Opens the drum discharge door to drop hot roasted beans onto the cooling tray.
    public void openDischargeDoor(boolean burnerStillOn) {
        if (burnerStillOn) {
            // Fire safety interlock: burner must be extinguished before opening discharge door.
            throw new IllegalStateException("SAFETY FAULT: Extinguish burner before opening discharge door!");
        }
        if (!coolingFanRunning) {
            // Quality interlock: beans keep roasting if dropped without active airflow.
            throw new IllegalStateException("Start cooling fan before discharging hot beans.");
        }
        this.dischargeDoorOpen = true;
        System.out.println("[CoolingTray] Discharge door OPENED. Beans transferred to cooling tray.");
    }

    // Closes the discharge door and stops the cooling fan once beans reach room temperature.
    public void finishCoolingCycle() {
        this.dischargeDoorOpen = false;
        this.coolingFanRunning = false;
        System.out.println("[CoolingTray] Discharge door CLOSED. Cooling fan STOPPED.");
    }
}
