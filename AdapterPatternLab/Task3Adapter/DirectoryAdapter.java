package Task3Adapter;

public class DirectoryAdapter implements IModernDirectory {

    private final LegacyStudentDirectory legacy;

    public DirectoryAdapter(LegacyStudentDirectory legacy) {
        this.legacy = legacy;
    }


    public DirectoryAdapter() {
        this(new LegacyStudentDirectory());
    }

    @Override
    public int size() {
        return legacy.totalEntries();
    }

    @Override
    public String getStudent(int zeroBasedIndex) {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= size()) {
            throw new IndexOutOfBoundsException(
                    "Modern index out of bounds: " + zeroBasedIndex
            );
        }


        int oneBasedIndex = zeroBasedIndex + 1;


        return legacy.getStudentAt(oneBasedIndex);
    }
}
