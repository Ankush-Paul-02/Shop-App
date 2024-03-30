package com.devmare.shop.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devmare.shop.exceptions.ResourceNotFoundException;
import com.devmare.shop.models.Coupon;
import com.devmare.shop.models.Inventory;
import com.devmare.shop.models.Order;
import com.devmare.shop.models.User;
import com.devmare.shop.repositories.CouponRepository;
import com.devmare.shop.repositories.InventoryRepository;
import com.devmare.shop.repositories.OrderRepository;
import com.devmare.shop.services.InventoryService;
import com.devmare.shop.services.OrderService;
import com.devmare.shop.services.UserService;

import lombok.Data;

@Data
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CouponRepository couponRepository;
    private final UserService userService;
    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;

    /**
     * This method is used to create an order.
     */
    @Override
    public Order createOrder(
            Integer userId,
            Integer quantity,
            String coupon) {
        // Get the inventory
        Inventory inventory = inventoryService.getInventory();
        // Check if the quantity is valid
        if (quantity < 1 || quantity > inventory.getAvailable()) {
            throw new IllegalArgumentException("Invalid quantity!");
        }

        String couponCode = coupon.substring(3);
        Coupon foundCoupon = null;
        try {
            foundCoupon = couponRepository.findByCouponDiscount(Integer.parseInt(couponCode));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid coupon code format");
        }

        // Check if the coupon is valid
        if (foundCoupon == null) {
            throw new IllegalArgumentException("Invalid coupon");
        }

        // Check if the coupon is already used by the user
        if (userService.hasUsedCoupon(userId, coupon)) {
            throw new IllegalArgumentException("Coupon already used by the user");
        }

        // Update the inventory
        inventory.setOrdered(inventory.getOrdered() + quantity);
        inventory.setAvailable(inventory.getAvailable() - quantity);

        inventoryRepository.save(inventory);

        User user = userService.getUserById(userId);
        userService.markCouponAsUsed(user.getId(), coupon);

        // Create the order
        Order order = new Order();
        order.setUserId(userId);
        order.setQuantity(quantity);
        order.setAmount(calculateAmount(quantity, inventory.getPrice(), foundCoupon.getCouponDiscount()));
        order.setCoupon(coupon);
        order.setDate(new Date());

        // Save the order
        return orderRepository.save(order);
    }

    @Override
    public List<Map<String, Object>> getOrdersByUserId(Integer userId) {
        // Get all orders by user id
        List<Order> orders = orderRepository.findAllByUserId(userId);

        // Map the orders to a list of maps
        return orders.stream().map(order -> {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("orderId", order.getId());
            orderMap.put("amount", order.getAmount());
            orderMap.put("date", order.getDate());
            orderMap.put("coupon", order.getCoupon());
            return orderMap;
        }).collect(Collectors.toList());
    }

    /**
     * This method is used to get an order by id.
     */
    @Override
    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order", "id", orderId));
    }

    /**
     * This method is used to calculate the amount of an order.
     * 
     * @param quantity
     * @param price
     * @param couponDiscount
     * @return calculated amount
     */
    private double calculateAmount(Integer quantity, Double price, Integer couponDiscount) {
        double totalPrice = quantity * price;
        double discountAmount = totalPrice * (couponDiscount / 100.0);
        return totalPrice - discountAmount;
    }
}
