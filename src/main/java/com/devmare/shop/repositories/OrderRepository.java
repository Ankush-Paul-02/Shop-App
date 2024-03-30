package com.devmare.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devmare.shop.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Find all orders by user id
    List<Order> findAllByUserId(Integer userId);
}
