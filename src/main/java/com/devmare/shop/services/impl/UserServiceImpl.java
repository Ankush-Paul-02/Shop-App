package com.devmare.shop.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devmare.shop.exceptions.ResourceNotFoundException;
import com.devmare.shop.models.User;
import com.devmare.shop.repositories.CouponRepository;
import com.devmare.shop.repositories.UserRepository;
import com.devmare.shop.services.UserService;

import lombok.Data;

@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    /**
     * This method is used to create a user.
     */
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * This method is used to get a user by id.
     */
    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id));
    }

    /**
     * This method is used to check if a user has used a coupon.
     */
    @Override
    public boolean hasUsedCoupon(Integer userId, String couponCode) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getUsedCoupons().stream()
                    .anyMatch(coupon -> coupon.equals(couponCode));
        }
        return false;
    }

    /**
     * This method is used to mark a coupon as used.
     */
    @Override
    public void markCouponAsUsed(Integer userId, String coupon) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.getUsedCoupons().add(coupon);
        userRepository.save(user);
    }
}
