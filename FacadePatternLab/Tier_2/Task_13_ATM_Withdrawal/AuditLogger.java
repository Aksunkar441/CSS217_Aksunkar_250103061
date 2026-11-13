import java.time.LocalDateTime;

public class AuditLogger {
    public void log(String cardNumber, double amount, boolean approved, String reason) {
        String maskedCard = cardNumber == null || cardNumber.length() < 4
                ? "unknown" : "****" + cardNumber.substring(cardNumber.length() - 4);
        System.out.println("[ATM AUDIT] " + LocalDateTime.now()
                + " | card=" + maskedCard
                + " | amount=$" + String.format("%.2f", amount)
                + " | result=" + (approved ? "APPROVED" : "REJECTED")
                + " | " + reason);
    }
}
