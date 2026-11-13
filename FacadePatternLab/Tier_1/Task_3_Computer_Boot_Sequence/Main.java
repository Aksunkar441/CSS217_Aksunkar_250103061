public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade(
                new PowerSupply(), new BIOS(), new HardDrive(), new OperatingSystem());
        computer.startComputer();
        computer.shutDownComputer();
    }
}
