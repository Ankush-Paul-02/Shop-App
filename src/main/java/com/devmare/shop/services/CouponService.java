package com.devmare.shop.services;

import java.util.Map;

import com.devmare.shop.models.Coupon;

public interface CouponService {
    /**
     * Create a new coupon
     * couponDiscount will be OFF5, OFF10, OFF15, OFF20, OFF25 in this format
     * 
     * @return coupon
     */
    Coupon createCoupon(Integer couponDiscount);

    /**
     * Get all coupons
     * 
     * @return coupons
     */
    Map<String, Integer> fetchCoupons();
}
