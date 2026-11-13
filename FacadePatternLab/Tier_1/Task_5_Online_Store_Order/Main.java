public class Main {
    public static void main(String[] args) {
        OrderFacade orders = new OrderFacade(
                new InventoryService(), new PaymentProcessor(), new ShippingService());
        orders.placeOrder("ITEM-100", 49.99, "12 Abay Avenue, Almaty");
        orders.placeOrder("ITEM-200", 129.50, "25 Dostyk Street, Almaty");
    }
}
