package com.devmare.shop.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.devmare.shop.models.Coupon;
import com.devmare.shop.repositories.CouponRepository;
import com.devmare.shop.services.CouponService;

import lombok.Data;

@Data
@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    /**
     * This method is used to create a coupon with a discount.
     */
    @Override
    public Coupon createCoupon(Integer couponDiscount) {
        Coupon coupon = new Coupon();
        coupon.setCouponDiscount(couponDiscount);
        couponRepository.save(coupon);
        return coupon;
    }

    /**
     * This method is used to fetch all coupons.
     */
    @Override
    public Map<String, Integer> fetchCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        Map<String, Integer> formattedCoupons = new HashMap<>();
        for (Coupon coupon : coupons) {
            formattedCoupons.put("OFF" + coupon.getCouponDiscount(), coupon.getCouponDiscount());
        }
        return formattedCoupons;
    }
}
