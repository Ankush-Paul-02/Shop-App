package com.devmare.shop.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmare.shop.constants.AppConstant;
import com.devmare.shop.models.Coupon;
import com.devmare.shop.services.CouponService;

import lombok.Data;

@Data
@RestController
@RequestMapping(AppConstant.BASE_URL)
public class CouponController {

    private final CouponService couponService;

    /**
     * This method is used to create a coupon.
     * 
     * @param couponDiscount
     * @return ResponseEntity<Coupon>
     */
    @PostMapping("coupon/{couponDiscount}")
    public ResponseEntity<Coupon> createCoupon(@PathVariable Integer couponDiscount) {
        return new ResponseEntity<>(couponService.createCoupon(couponDiscount), HttpStatus.CREATED);
    }

    /**
     * This method is used to fetch coupons.
     * 
     * @return ResponseEntity<Map<String, Integer>>
     */
    @GetMapping("fetchCoupons")
    public ResponseEntity<Map<String, Integer>> fetchCoupons() {
        return ResponseEntity.ok(couponService.fetchCoupons());
    }

}
