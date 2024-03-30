package com.devmare.shop.services;

import java.util.Map;

public interface PaymentService {

    /**
     * This method is used to process the payment.
     * 
     * @param userId
     * @param orderId
     * @param amount
     * @return Map<String, Object>
     */
    Map<String, Object> processPayment(Integer userId, Integer orderId, Integer amount);
}
