package com.inventory.book.repository;

import com.inventory.book.entity.PurchaseHistory;
import com.inventory.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    List<PurchaseHistory> findByUser(User user);
}
