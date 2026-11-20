// Subsystem 1: Controls the pneumatic gate that drops green coffee beans into the drum.
public class BeanHopperGate {
    // Tracks whether the gate is currently open (true) or closed (false).
    private boolean gateOpen = false;
    // Tracks the current weight of green beans staged in the hopper (in grams).
    private int stagedBeansGrams = 0;

    // Loads raw green coffee beans into the upper hopper while the gate is closed.
    public void loadGreenBeans(int grams) {
        if (gateOpen) {
            // Safety interlock: cannot load beans while the drop gate is open.
            throw new IllegalStateException("Cannot load beans while hopper gate is open.");
        }
        if (grams < 250 || grams > 5000) {
            // Hardware capacity constraint: minimum 250g batch, maximum 5000g batch.
            throw new IllegalArgumentException("Batch size must be between 250g and 5000g.");
        }
        this.stagedBeansGrams = grams;
        System.out.println("[HopperGate] Loaded " + grams + "g of green beans into hopper.");
    }

    // Opens the gate to release staged beans into the roasting drum.
    public void openGate() {
        if (stagedBeansGrams == 0) {
            // Precondition: prevents dry-running the roaster with an empty batch.
            throw new IllegalStateException("Cannot open gate: hopper is empty.");
        }
        this.gateOpen = true;
        System.out.println("[HopperGate] Gate OPENED. Dropping " + stagedBeansGrams + "g into drum.");
        // Once dropped into the drum, the upper hopper is now empty.
        this.stagedBeansGrams = 0;
    }

    // Closes and seals the hopper gate before high-heat roasting begins.
    public void closeGate() {
        this.gateOpen = false;
        System.out.println("[HopperGate] Gate CLOSED and sealed.");
    }

    // Read-only accessor used by safety checks in other subsystems or the Facade.
    public boolean isGateOpen() {
        return gateOpen;
    }
}
