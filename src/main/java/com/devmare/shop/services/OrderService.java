package com.devmare.shop.services;

import java.util.List;
import java.util.Map;

import com.devmare.shop.models.Order;

public interface OrderService {

    /**
     * This method is used to create an order.
     * 
     * @param userId
     * @param quantity
     * @param coupon
     * @return Order
     */
    Order createOrder(Integer userId, Integer quantity, String coupon);

    /**
     * This method is used to get all orders by user id.
     * 
     * @param userId
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getOrdersByUserId(Integer userId);

    /**
     * This method is used to get order by order id.
     * 
     * @param orderId
     * @return Order
     */
    Order getOrderById(Integer orderId);
}
