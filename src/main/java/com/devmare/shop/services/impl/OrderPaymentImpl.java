package com.devmare.shop.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devmare.shop.models.OrderPayment;
import com.devmare.shop.repositories.OrderPaymentRepository;
import com.devmare.shop.repositories.OrderRepository;
import com.devmare.shop.services.OrderPaymentService;

import lombok.Data;

@Data
@Service
public class OrderPaymentImpl implements OrderPaymentService {

    private final OrderPaymentRepository orderPaymentRepository;
    private final OrderRepository orderRepository;

    // This method is used to save the order payment.
    @Override
    public void saveOrderPayment(OrderPayment orderPayment) {
        orderPaymentRepository.save(orderPayment);
    }

    // This method is used to get all orders by user id.
    @Override
    public List<OrderPayment> getAllOrdersByUserId(Integer userId, Integer orderId) {
        return orderPaymentRepository.findAllOrderPaymentByOrderId(orderId);
    }

}
