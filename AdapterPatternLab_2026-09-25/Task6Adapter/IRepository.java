package Task6Adapter;

public interface IRepository {
    String findById(int id) throws RecordNotFoundException, DatabaseLockedException;
}
