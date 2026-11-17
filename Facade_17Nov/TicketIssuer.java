// Subsystem 5: Generates the final e-ticket confirmation string.
public class TicketIssuer {
    public String generateTicket(String passengerName,
                                 String flightCode,
                                 String seatLockToken,
                                 String paymentReceiptId) {
        if (seatLockToken == null || paymentReceiptId == null) {
            throw new IllegalStateException("Cannot issue ticket without seat lock and payment.");
        }
        String ticketCode = "ETKT-" + flightCode.toUpperCase() + "-" +
                paymentReceiptId.substring(3);
        System.out.println("[TicketIssuer] Issued " + ticketCode + " for " + passengerName);
        return ticketCode;
    }
}