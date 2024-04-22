package com.inventory.book.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.inventory.book.entity.Book;
import com.inventory.book.entity.Cart;
import com.inventory.book.entity.PurchaseHistory;
import com.inventory.book.entity.User;
import com.inventory.book.repository.CartRepository;
import com.inventory.book.repository.PurchaseHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Spy
    @InjectMocks
    private CheckoutService checkoutService;

    @Test
    public void testCheckout_SuccessfulPayment() {
        // Given
        User user = new User();
        Book book = new Book();
        Cart cartItem = new Cart(user, book, 2, 15.99); // Assuming constructor Cart(User, Book, quantity, price)
        List<Cart> cartItems = Arrays.asList(cartItem);
        when(cartRepository.findByUser(user)).thenReturn(cartItems);
        doReturn(true).when(checkoutService).simulatePayment(cartItems, "WEB");

        // When
        checkoutService.checkout(user, "WEB");

        // Then
        verify(purchaseHistoryRepository).save(any(PurchaseHistory.class));
        verify(cartRepository).delete(cartItem);
    }

    @Test
    public void testCheckout_PaymentFailed() {
        // Given
        User user = new User();
        List<Cart> cartItems = Arrays.asList(new Cart(user, new Book(), 1, 20.00));
        when(cartRepository.findByUser(user)).thenReturn(cartItems);
        doReturn(false).when(checkoutService).simulatePayment(cartItems, "WEB");

        // Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            // When
            checkoutService.checkout(user, "WEB");
        });

        assertEquals("Payment failed. Please try again.", exception.getMessage());
        verify(purchaseHistoryRepository, never()).save(any(PurchaseHistory.class));
        verify(cartRepository, never()).delete(any(Cart.class));
    }

}

