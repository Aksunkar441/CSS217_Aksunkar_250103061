package Task16Adapter;

public class LocalDiskFileSystem {
    public boolean saveToPath(String fullPath, byte[] data) {
        System.out.println("Saving " + data.length + " bytes to " + fullPath);
        return true;
    }
}
