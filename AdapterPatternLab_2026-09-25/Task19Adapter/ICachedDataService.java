package Task19Adapter;

public interface ICachedDataService {
    String read(int id);
    int getCacheHitCount();
}
