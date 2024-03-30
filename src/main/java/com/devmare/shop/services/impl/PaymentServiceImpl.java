package com.devmare.shop.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.devmare.shop.models.Order;
import com.devmare.shop.models.OrderPayment;
import com.devmare.shop.services.OrderPaymentService;
import com.devmare.shop.services.OrderService;
import com.devmare.shop.services.PaymentService;

import lombok.Data;

@Data
@Service
public class PaymentServiceImpl implements PaymentService {

    private final Random random = new Random();

    private final OrderPaymentService orderPaymentService;
    private final OrderService orderService;

    /**
     * This method is used to process the payment.
     */
    @Override
    public Map<String, Object> processPayment(Integer userId, Integer orderId, Integer amount) {
        int statusCode = random.nextInt(6);
        return generatePaymentResponse(statusCode, userId, orderId, amount);
    }

    /**
     * This method is used to generate the payment response.
     * 
     * @param statusCode
     * @param userId
     * @param orderId
     * @param amount
     * @return Map<String, Object> response
     */
    private Map<String, Object> generatePaymentResponse(int statusCode, Integer userId, Integer orderId,
            Integer amount) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("orderId", orderId);

        String status;
        String transactionId;
        String description = null;
        int httpStatusCode;

        switch (statusCode) {
            case 0:
                status = "successful";
                transactionId = "tran010100001";
                httpStatusCode = 200;
                break;
            case 1:
                status = "failed";
                transactionId = "tran010100002";
                description = "Payment Failed as amount is invalid";
                httpStatusCode = 400;
                break;
            case 2:
                status = "failed";
                transactionId = "tran010100003";
                description = "Payment Failed from bank";
                httpStatusCode = 400;
                break;
            case 3:
                status = "failed";
                transactionId = "tran010100004";
                description = "Payment Failed due to invalid order id";
                httpStatusCode = 400;
                break;
            case 4:
                status = "failed";
                transactionId = "tran010100005";
                description = "No response from payment server";
                httpStatusCode = 400;
                break;
            case 5:
                status = "failed";
                transactionId = "tran010100006";
                description = "Order is already paid for";
                httpStatusCode = 405;
                break;
            default:
                status = "failed";
                transactionId = "unknown";
                description = "An unexpected error occurred";
                httpStatusCode = 500;
        }

        // Add the response details to the response map
        response.put("status", status);
        response.put("transactionId", transactionId);
        response.put("statusCode", httpStatusCode);
        if (description != null) {
            response.put("description", description);
        }

        // Save the order payment
        Order order = orderService.getOrderById(orderId);

        // Save the order payment
        OrderPayment orderPayment = new OrderPayment();
        orderPayment.setOrderId(orderId);
        orderPayment.setAmount(amount);
        orderPayment.setCoupon(order.getCoupon());
        orderPayment.setTransactionId(transactionId);
        orderPayment.setStatus(status);
        orderPayment.setDate(order.getDate());

        // Save the order payment
        orderPaymentService.saveOrderPayment(orderPayment);

        return response;
    }
}
