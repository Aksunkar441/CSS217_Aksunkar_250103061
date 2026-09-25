package Task16Adapter;

public class LocalStorageBlobAdapter implements ICloudBlobStorage {
    private final LocalDiskFileSystem fileSystem;

    public LocalStorageBlobAdapter(LocalDiskFileSystem fileSystem) { this.fileSystem = fileSystem; }
    public LocalStorageBlobAdapter() { this(new LocalDiskFileSystem()); }

    @Override
    public boolean uploadBlob(String bucketName, String objectKey, byte[] data) {
        String path = ("/var/data/" + bucketName + "/" + objectKey).replaceAll("/+/", "/");
        return fileSystem.saveToPath(path, data);
    }
}
