package com.inventory.book.controller;

import com.inventory.book.dto.CartDTO;
import com.inventory.book.entity.Book;
import com.inventory.book.entity.Cart;
import com.inventory.book.entity.User;
import com.inventory.book.service.BookService;
import com.inventory.book.service.CartService;
import com.inventory.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;  // Assume this handles fetching user details

    @Autowired
    private BookService bookService;  // Assume this handles fetching book details

    @PostMapping("/add")
    public String addToCart(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam int quantity) {
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);
        cartService.addToCart(user, book, quantity);
        return "Added to cart";
    }

    @GetMapping("/view")
    public List<CartDTO> viewCart(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        return cartService.getCartDTOsByUser(user);
    }
}
