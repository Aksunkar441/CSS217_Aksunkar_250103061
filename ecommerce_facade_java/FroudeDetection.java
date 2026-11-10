package com.ecommerce;

public class FroudeDetection {

    public boolean isSus(String customerId, double amount){
        if (amount >= 100_000){
            return false;
        }
        return true;
    }
}
