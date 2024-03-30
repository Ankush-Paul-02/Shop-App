package com.devmare.shop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmare.shop.constants.AppConstant;
import com.devmare.shop.models.User;
import com.devmare.shop.services.UserService;

import lombok.Data;

@Data
@RestController
@RequestMapping(AppConstant.BASE_URL)
public class UserController {

    private final UserService userService;

    /**
     * This method is used to create a user.
     */
    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    /**
     * This method is used to get the user by id.
     */
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }

    /**
     * This method is used to check if a user has used a coupon.
     */
    @GetMapping("user/{id}/{coupon}")
    public ResponseEntity<String> hasUserUsedCoupon(
            @PathVariable Integer id,
            @PathVariable String coupon) {
        boolean hasUsedCoupon = userService.hasUsedCoupon(id, coupon);
        if (hasUsedCoupon) {
            return ResponseEntity.ok("User has already used the coupon!");
        }
        return ResponseEntity.ok("User has not used the coupon yet!");
    }
}
