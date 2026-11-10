package com.ecommerce;

/**
 * Subsystem Class 2: PaymentGateway
 * Responsible for processing financial transactions and communicating with payment processors.
 */
public class PaymentGateway {

    /**
     * Step 1: Charge the customer's payment method for the specified order total.
     *
     * @param customerId The customer account or token ID.
     * @param amount The monetary amount to debit.
     * @return true if the transaction succeeded, false if payment failed/declined.
     */
    public boolean charge(String customerId, double amount) {
        // Step 1.1: Validate payment method and customer token
        System.out.println("[PaymentGateway] Step 1: Initiating transaction of $" + amount + " for customer: " + customerId);
        
        // Step 1.2: Perform bank charge and capture response (mocked as true)
        System.out.println("[PaymentGateway] Step 2: Payment captured successfully for customer: " + customerId);
        return true;
    }
}
