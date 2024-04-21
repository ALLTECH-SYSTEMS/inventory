package com.inventory.book.dto;

import java.util.Date;

public class PurchaseHistoryDTO {
    private Long id;
    private String bookTitle;
    private Long bookId;
    private int quantity;
    private double price;
    private Date purchaseDate;

    public PurchaseHistoryDTO(Long id, String bookTitle, Long bookId, int quantity, double price, Date purchaseDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Long getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }
}
