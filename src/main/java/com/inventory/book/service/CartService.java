package com.inventory.book.service;

import com.inventory.book.dto.CartDTO;
import com.inventory.book.entity.Book;
import com.inventory.book.entity.Cart;
import com.inventory.book.entity.User;
import com.inventory.book.repository.CartRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public void addToCart(User user, Book book, int quantity) {
        List<Cart> existingItems = cartRepository.findByUser(user);
        for (Cart item : existingItems) {
            if (item.getBook().getId().equals(book.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                cartRepository.save(item);
                return;
            }
        }
        Cart newItem = new Cart();
        newItem.setUser(user);
        newItem.setBook(book);
        newItem.setQuantity(quantity);
        cartRepository.save(newItem);
    }

    public List<CartDTO> getCartDTOsByUser(User user) {
        return cartRepository.findByUser(user).stream()
                .map(cart -> new CartDTO(cart.getId(), cart.getQuantity(), cart.getBook().getTitle(), cart.getBook().getId(), cart.getTotalPrice()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeFromCart(User user, Book book, int quantity) {
        List<Cart> existingItems = cartRepository.findByUser(user);
        for (Cart item : existingItems) {
            if (item.getBook().getId().equals(book.getId())) {
                int remainingQuantity = item.getQuantity() - quantity;
                if (remainingQuantity > 0) {
                    item.setQuantity(remainingQuantity);
                    cartRepository.save(item);
                } else {
                    // If the remaining quantity is less than or equal to zero, remove the item completely
                    cartRepository.delete(item);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Item not found in cart or quantity to remove exceeds the existing quantity");
    }
}
