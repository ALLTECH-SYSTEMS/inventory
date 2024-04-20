package com.inventory.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.inventory.book.entity.Book;
import com.inventory.book.service.BookService;


@RestController
@RequestMapping("/books")
public class BookController {
    
     @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String genre) {
        return bookService.searchBooks(title, author, year, genre);
    }
}
