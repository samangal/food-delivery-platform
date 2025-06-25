package com.fooddelivery.inventoryservice.service;

import com.fooddelivery.inventoryservice.model.Inventory;
import com.fooddelivery.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    public Optional<Inventory> getInventoryByProductId(Long productId) {
        return repository.findByProductId(productId);
    }

    public Inventory updateInventory(Long productId, int quantity) {
        Inventory inventory = repository.findByProductId(productId)
                .orElse(new Inventory(null, productId, 0));
        inventory.setQuantity(quantity);
        return repository.save(inventory);
    }
}
