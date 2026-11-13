import java.util.Objects;

// The facade coordinates the order and releases stock if payment is rejected.
public class OrderFacade {
    private final InventoryService inventory;
    private final PaymentProcessor payment;
    private final ShippingService shipping;

    public OrderFacade(InventoryService inventory, PaymentProcessor payment,
                       ShippingService shipping) {
        this.inventory = Objects.requireNonNull(inventory);
        this.payment = Objects.requireNonNull(payment);
        this.shipping = Objects.requireNonNull(shipping);
    }

    public boolean placeOrder(String itemId, double amount, String address) {
        Objects.requireNonNull(itemId, "itemId");
        Objects.requireNonNull(address, "address");
        System.out.println("\nPlacing order for " + itemId + ".");
        if (!inventory.reserveStock(itemId)) return false;
        if (!payment.chargeCustomer(amount)) {
            inventory.releaseStock(itemId);
            return false;
        }
        shipping.generateShippingLabel(address);
        System.out.println("Order placed successfully.");
        return true;
    }
}
