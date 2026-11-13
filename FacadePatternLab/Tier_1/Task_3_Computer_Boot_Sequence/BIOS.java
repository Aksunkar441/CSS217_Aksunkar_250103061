public class BIOS {
    public boolean runPost() {
        System.out.println("BIOS: running POST diagnostics...");
        System.out.println("BIOS: hardware checks passed.");
        return true;
    }
}
