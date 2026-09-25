package Task7Adapter;

import java.util.Enumeration;
import java.util.Vector;

public class LegacyInventory {
    private final Vector<String> items = new Vector<>();
    public LegacyInventory() { items.add("GPU"); items.add("CPU"); items.add("RAM"); }
    public Enumeration<String> getCatalogEnumeration() { return items.elements(); }
}
