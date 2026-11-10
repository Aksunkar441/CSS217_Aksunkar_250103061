package com.ecommerce;

/**
 * Subsystem Class 1: InventoryService
 * Responsible for verifying and allocating product inventory.
 */
public class InventoryService {

    /**
     * Step 1: Check whether sufficient stock is available in the warehouse.
     *
     * @param productId The unique identifier of the item.
     * @return true if item is in stock and available for order, false otherwise.
     */
    public boolean checkStock(String productId) {
        // Step 1.1: Query warehouse database/inventory cache
        System.out.println("[InventoryService] Step 1: Checking stock levels for item: " + productId);
        
        // Step 1.2: Return stock status (mocked as true for this demonstration)
        return true;
    }

    /**
     * Step 2: Reserve the requested stock so subsequent concurrent orders do not oversell.
     *
     * @param productId The unique identifier of the item.
     */
    public void reserve(String productId) {
        // Step 2.1: Lock and decrement available stock count
        System.out.println("[InventoryService] Step 2: Stock reserved successfully for item: " + productId);
    }
}
