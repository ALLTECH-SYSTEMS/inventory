package com.inventory.book.service;

import com.inventory.book.entity.Cart;
import com.inventory.book.entity.PurchaseHistory;
import com.inventory.book.entity.User;
import com.inventory.book.repository.CartRepository;
import com.inventory.book.repository.PurchaseHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Transactional
    public void checkout(User user, String paymentMethod) {
        List<Cart> cartItems = cartRepository.findByUser(user);

        // Simulate payment process based on paymentMethod
        if (simulatePayment(cartItems, paymentMethod)) {
            cartItems.forEach(item -> {

                PurchaseHistory history = new PurchaseHistory();
                history.setUser(user);
                history.setBook(item.getBook());
                history.setQuantity(item.getQuantity());
                history.setPrice(item.getTotalPrice());
                history.setPurchaseDate(new Date());
                purchaseHistoryRepository.save(history);

                cartRepository.delete(item);  // Clear the cart item after purchase
            });
        } else {
            throw new RuntimeException("Payment failed. Please try again.");
        }
    }

    private boolean simulatePayment(List<Cart> cartItems, String paymentMethod) {
        // Simulation logic for payment
        return true; // Simulate success for now
    }
}
