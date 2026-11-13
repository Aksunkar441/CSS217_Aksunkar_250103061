import java.util.Objects;

// Main only needs two calls; this class handles the hardware sequence.
public class ComputerFacade {
    private final PowerSupply powerSupply;
    private final BIOS bios;
    private final HardDrive hardDrive;
    private final OperatingSystem operatingSystem;
    private boolean running;

    public ComputerFacade(PowerSupply powerSupply, BIOS bios,
                          HardDrive hardDrive, OperatingSystem operatingSystem) {
        this.powerSupply = Objects.requireNonNull(powerSupply);
        this.bios = Objects.requireNonNull(bios);
        this.hardDrive = Objects.requireNonNull(hardDrive);
        this.operatingSystem = Objects.requireNonNull(operatingSystem);
    }

    public void startComputer() {
        if (running) {
            System.out.println("Computer is already running.");
            return;
        }
        System.out.println("\nStarting computer...");
        powerSupply.turnOn();
        if (!bios.runPost()) {
            System.out.println("POST failed. Startup cancelled.");
            powerSupply.turnOff();
            return;
        }
        hardDrive.spinUp();
        hardDrive.readBootSector();
        operatingSystem.loadKernel();
        running = true;
    }

    public void shutDownComputer() {
        if (!running) {
            System.out.println("Computer is already off.");
            return;
        }
        System.out.println("\nShutting down computer...");
        operatingSystem.shutDown();
        hardDrive.stop();
        powerSupply.turnOff();
        running = false;
    }
}
