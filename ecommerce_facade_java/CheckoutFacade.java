package com.ecommerce;

/**
 * The Facade Class: CheckoutFacade
 * Coordinates the underlying subsystems (Inventory, Payment, Shipping, Notification)
 * behind a unified, single-method interface for callers.
 */
public class CheckoutFacade {

    // Subsystem instances maintained by the Facade
    private final InventoryService inventory;
    private final PaymentGateway payment;
    private final ShippingService shipping;
    private final NotificationService notification;
    private final FroudeDetection froude;

    /**
     * Step 1: Initialize all internal subsystem dependencies.
     */
    public CheckoutFacade() {
        this.inventory = new InventoryService();
        this.payment = new PaymentGateway();
        this.shipping = new ShippingService();
        this.notification = new NotificationService();
        this.froude = new FroudeDetection();
    }

    /**
     * Step 2: Unified checkout orchestrator.
     * Coordinates the full multi-service workflow in the proper sequence.
     *
     * @param productId Identifier of the purchased item.
     * @param customerId Identifier of the purchasing user.
     * @param address Physical delivery destination.
     * @param amount Total transaction charge.
     * @return true if the entire checkout workflow succeeded, false otherwise.
     */
    public boolean placeOrder(String productId, String customerId, String address, double amount) {
        System.out.println("=== [CheckoutFacade] Starting Order Placement Workflow ===");

        // Step 2.1: Verify warehouse inventory availability
        if (!inventory.checkStock(productId)) {
            System.out.println("[CheckoutFacade] Aborting: Item out of stock.");
            return false;
        }



        // Step 2.2: Reserve the item in stock
        inventory.reserve(productId);



        // Step 2.3: Process billing and charge customer
        if (!payment.charge(customerId, amount) && !froude.isSus()) {
            System.out.println("[CheckoutFacade] Aborting: Payment declined.");
            return false;
        }


        // Step 2.4: Schedule logistics carrier dispatch
        shipping.scheduleDelivery(productId, address);

        // Step 2.5: Dispatch digital receipt confirmation
        notification.sendReceipt(customerId, amount);

        System.out.println("=== [CheckoutFacade] Order Workflow Completed Successfully ===");
        return true;
    }
}
