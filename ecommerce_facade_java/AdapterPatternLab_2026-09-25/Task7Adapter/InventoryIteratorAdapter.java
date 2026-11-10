package Task7Adapter;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InventoryIteratorAdapter implements IInventoryIteratorProvider {
    private final LegacyInventory inventory;

    public InventoryIteratorAdapter(LegacyInventory inventory) { this.inventory = inventory; }
    public InventoryIteratorAdapter() { this(new LegacyInventory()); }

    @Override
    public Iterator<String> getInventoryIterator() {
        Enumeration<String> enumeration = inventory.getCatalogEnumeration();
        return new Iterator<>() {
            @Override public boolean hasNext() { return enumeration.hasMoreElements(); }
            @Override public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                return enumeration.nextElement();
            }
        };
    }
}
