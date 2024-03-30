package com.devmare.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devmare.shop.models.OrderPayment;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Integer> {

    // Find all order payments by order id
    List<OrderPayment> findAllOrderPaymentByOrderId(Integer orderId);
}
