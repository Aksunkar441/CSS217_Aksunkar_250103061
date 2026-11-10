package com.ecommerce;

/**
 * Subsystem Class 4: NotificationService
 * Responsible for sending transactional communications (email, SMS, push) to customers.
 */
public class NotificationService {

    /**
     * Step 1: Generate and transmit an order receipt/confirmation to the customer.
     *
     * @param customerId The customer receiving the notification.
     * @param amount The total charged amount to display in the receipt.
     */
    public void sendReceipt(String customerId, double amount) {
        // Step 1.1: Render digital receipt template
        System.out.println("[NotificationService] Step 1: Compiling receipt for customer: " + customerId);
        
        // Step 1.2: Dispatch email/SMS notification
        System.out.println("[NotificationService] Step 2: Receipt of $" + amount + " dispatched to customer: " + customerId);
    }
}
