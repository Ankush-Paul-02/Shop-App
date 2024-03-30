package com.devmare.shop.services;

import java.util.List;

import com.devmare.shop.models.OrderPayment;

public interface OrderPaymentService {

    /**
     * This method is used to save the order payment.
     * 
     * @param orderPayment
     */
    void saveOrderPayment(OrderPayment orderPayment);

    /**
     * This method is used to get all orders by user id.
     * 
     * @param userId
     * @param orderId
     * @return List<OrderPayment>
     */
    List<OrderPayment> getAllOrdersByUserId(Integer userId, Integer orderId);
}
