package com.devmare.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devmare.shop.models.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    // This method is used to find a coupon by its discount. Example: 5, 10, 15
    Coupon findByCouponDiscount(Integer couponDiscount);
}
