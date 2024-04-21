package com.inventory.book.dto;

public class CartDTO {
    private Long id;
    private int quantity;
    private String bookTitle;

    public CartDTO(Long id, int quantity, String bookTitle) {
        this.id = id;
        this.quantity = quantity;
        this.bookTitle = bookTitle;
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
}
