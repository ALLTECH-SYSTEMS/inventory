package com.inventory.book.dto;

public class CartDTO {
    private Long id;
    private int quantity;
    private String bookTitle;
    private Long bookId;
    private double price;

    public CartDTO(Long id, int quantity, String bookTitle, Long bookId, double price) {
        this.id = id;
        this.quantity = quantity;
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public double getPrice() {
        return price;
    }

    public Long getBookId() {
        return bookId;
    }
}
