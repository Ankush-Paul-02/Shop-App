package com.devmare.shop.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devmare.shop.models.OrderPayment;
import com.devmare.shop.services.OrderPaymentService;
import com.devmare.shop.services.PaymentService;

import lombok.Data;

@Data
@RestController
@RequestMapping("/api/v1/shop")
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderPaymentService orderPaymentService;

    @PostMapping("{userId}/{orderId}/pay")
    public ResponseEntity<Map<String, Object>> processPayment(
            @RequestParam Integer amount,
            @PathVariable Integer userId,
            @PathVariable Integer orderId) {

        Map<String, Object> paymentResponse = paymentService.processPayment(userId, orderId, amount);
        int statusCode = (int) paymentResponse.get("statusCode");
        paymentResponse.remove("statusCode");

        return ResponseEntity.status(statusCode).body(paymentResponse);
    }

    @GetMapping("{userId}/orders/{orderId}")
    public ResponseEntity<?> getOrderPaymentsByUserIdOrderId(
            @PathVariable Integer userId,
            @PathVariable Integer orderId) {
        List<OrderPayment> orderPayments = orderPaymentService.getAllOrdersByUserId(userId, orderId);
        if (orderPayments.isEmpty()) {
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("orderId", orderId);
            errorBody.put("description", "Order not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
        return ResponseEntity.ok(orderPayments);
    }
}
