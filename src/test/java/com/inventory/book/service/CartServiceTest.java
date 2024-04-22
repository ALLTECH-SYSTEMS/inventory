package com.inventory.book.service;

import com.inventory.book.entity.Book;
import com.inventory.book.entity.Cart;
import com.inventory.book.entity.User;
import com.inventory.book.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void testAddToCart_NewItem() {
        // Setup
        User user = new User();  
        Book book = new Book();  
        book.setId(1L);
        when(cartRepository.findByUser(user)).thenReturn(new ArrayList<>());

        // Execute
        cartService.addToCart(user, book, 1);

        // Verify
        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    public void testAddToCart_ExistingItem() {
        // Setup
        User user = new User();
        Book book = new Book();
        book.setId(1L);
        Cart existingCart = new Cart();
        existingCart.setUser(user);
        existingCart.setBook(book);
        existingCart.setQuantity(1);

        when(cartRepository.findByUser(user)).thenReturn(Arrays.asList(existingCart));

        // Execute
        cartService.addToCart(user, book, 1);

        // Verify
        verify(cartRepository).save(existingCart);
        assertEquals(2, existingCart.getQuantity(), "The quantity should be updated to 2");
    }

    @Test
    public void testAddToCart_UpdatingExistingItem() {
        // Setup
        Long bookId = 1L;
        User user = new User(); 
        Book book = new Book();
        book.setId(bookId);
        Cart existingItem = new Cart();
        existingItem.setBook(book);
        existingItem.setQuantity(1);

        List<Cart> cartItems = new ArrayList<>();
        cartItems.add(existingItem);

        when(cartRepository.findByUser(user)).thenReturn(cartItems);

        // Execute
        cartService.addToCart(user, book, 2);  // Attempting to add 2 more of the same book

        // Verify
        verify(cartRepository).save(existingItem);
        assertEquals(3, existingItem.getQuantity(), "The quantity of the existing item should be incremented by 2");
    }
}
