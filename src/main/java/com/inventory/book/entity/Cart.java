package com.inventory.book.entity;

import com.inventory.book.enums.Genre;
import jakarta.persistence.*;

@Entity
public class Cart extends RootEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalPrice;


    // Default constructor for JPA
    public Cart() {
    }

    public Cart(User user, Book book, int quantity, double totalPrice) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    //Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        updateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void updateTotalPrice() {
        if (book != null) {
            this.totalPrice = this.quantity * this.book.getPrice();
        }
    }
}
