package com.inventory.book.controller;

import com.inventory.book.entity.User;
import com.inventory.book.service.CheckoutService;
import com.inventory.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> performCheckout(@RequestParam Long userId, @RequestParam String paymentMethod) {
        User user = userService.getUserById(userId);

        try {
            checkoutService.checkout(user, paymentMethod);
            return ResponseEntity.ok("Checkout successful using " + paymentMethod + " payment method.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
