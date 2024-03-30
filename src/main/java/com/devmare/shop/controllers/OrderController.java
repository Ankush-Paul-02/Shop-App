package com.devmare.shop.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devmare.shop.constants.AppConstant;
import com.devmare.shop.models.Order;
import com.devmare.shop.services.OrderService;

import lombok.Data;

@Data
@RestController
@RequestMapping(AppConstant.BASE_URL)
public class OrderController {

    private final OrderService orderService;

    /**
     * This method is used to create an order.
     *
     * @param quantity
     * @param coupon
     * @param userId
     * @return ResponseEntity<Order>
     */
    @PostMapping("{userId}/order")
    public ResponseEntity<Order> createOrder(
            @RequestParam("qty") Integer quantity,
            @RequestParam("coupon") String coupon,
            @PathVariable Integer userId) {
        Order order = orderService.createOrder(userId, quantity, coupon);
        return ResponseEntity.ok(order);
    }

    /**
     * This method is used to get all orders by user id.
     *
     * @param userId
     * @return List<Map<String, Object>>
     */
    @GetMapping("{userId}/orders")
    public List<Map<String, Object>> getOrdersByUserId(@PathVariable Integer userId) {
        return orderService.getOrdersByUserId(userId);
    }
}
