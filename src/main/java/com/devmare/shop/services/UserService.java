package com.devmare.shop.services;

import com.devmare.shop.models.User;

public interface UserService {

    /**
     * This method is used to create a user.
     * 
     * @param user
     * @return User
     */
    User createUser(User user);

    /**
     * This method is used to get the user by id.
     * 
     * @param id
     * @return User
     */
    User getUserById(Integer id);

    /**
     * This method is used to get the user by email.
     * 
     * @param email
     * @return User
     */
    boolean hasUsedCoupon(Integer userId, String couponCode);

    /**
     * This method is used to mark the coupon as used.
     * 
     * @param userId
     * @param coupon
     */
    void markCouponAsUsed(Integer userId, String coupon);
}
