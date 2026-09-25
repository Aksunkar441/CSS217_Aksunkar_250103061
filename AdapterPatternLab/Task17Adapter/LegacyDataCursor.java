package Task17Adapter;

public class LegacyDataCursor {
    public boolean next() { return true; }
    public String getString(String colName) { return "CS301"; }
    public int getInt(String colName) { return 95; }
}
