// Client: Simulates the roaster operator touchscreen.
// IMPORTANT: Must ONLY interact with CoffeeRoasterFacade — never with individual hardware classes.
public class Main {
    public static void main(String[] args) {

        CoffeeRoasterFacade roaster = new CoffeeRoasterFacade();


        System.out.println("=== BATCH 1: Light Roast Preset (Ethiopia Sidamo) ===");
        boolean batch1Success = roaster.roastLightProfile("Ethiopia Sidamo", 1200);
        System.out.println("Batch 1 Completed Successfully: " + batch1Success + "\n");


        System.out.println("=== BATCH 2: Custom Medium Roast (Colombia Supremo) ===");
        boolean batch2Success = roaster.runRoastBatch("Colombia Supremo", 2500, 48, 212);
        System.out.println("Batch 2 Completed Successfully: " + batch2Success + "\n");


        System.out.println("=== BATCH 3: Over-Temperature Fault & Emergency Abort Test ===");
        // 280°C exceeds the 245°C safety ceiling inside HeatingChamber.
        // The drum is already spinning and beans are inside when heatToTarget(280) throws.
        // emergencyAbort() must extinguish the burner and stop the drum cleanly.
        boolean batch3Success = roaster.runRoastBatch("Brazil Santos", 1500, 50, 280);
        System.out.println("Batch 3 Completed Successfully: " + batch3Success + "\n");


        System.out.println("=== BATCH 4: Recovery After Abort (Dark Roast Preset) ===");
        // Confirms that after Batch 3 aborted, all subsystems were reset to a valid idle state
        // so subsequent batches can run without leftover open gates or state errors.
        boolean batch4Success = roaster.roastDarkProfile("Sumatra Mandheling", 1800);
        System.out.println("Batch 4 Completed Successfully: " + batch4Success + "\n");
    }
}
