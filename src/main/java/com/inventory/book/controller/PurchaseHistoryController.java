package com.inventory.book.controller;

import com.inventory.book.dto.PurchaseHistoryDTO;
import com.inventory.book.entity.PurchaseHistory;
import com.inventory.book.entity.User;
import com.inventory.book.service.PurchaseHistoryService;
import com.inventory.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/purchase")
public class PurchaseHistoryController {

    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<PurchaseHistoryDTO>> getPurchaseHistory(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<PurchaseHistoryDTO> history = purchaseHistoryService.getPurchaseHistoryDTOsByUser(user);
        return ResponseEntity.ok(history);
    }
}
