package com.inventory.book.service;

import com.inventory.book.dto.PurchaseHistoryDTO;
import com.inventory.book.entity.PurchaseHistory;
import com.inventory.book.entity.User;
import com.inventory.book.repository.PurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseHistoryService {

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    public List<PurchaseHistoryDTO> getPurchaseHistoryDTOsByUser(User user) {
        return purchaseHistoryRepository.findByUser(user).stream()
                .map(history -> new PurchaseHistoryDTO(
                        history.getId(),
                        history.getBook().getTitle(),
                        history.getBook().getId(),
                        history.getQuantity(),
                        history.getPrice(),
                        history.getPurchaseDate()))
                .collect(Collectors.toList());
    }
}
