public class ShippingService {
    public String generateShippingLabel(String address) {
        String label = "SHIP-" + Math.abs(address.hashCode());
        System.out.println("Shipping label " + label + " created for " + address + ".");
        return label;
    }
}
