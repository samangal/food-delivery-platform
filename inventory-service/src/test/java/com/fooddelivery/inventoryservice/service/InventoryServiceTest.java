package com.fooddelivery.inventoryservice.service;

import com.fooddelivery.inventoryservice.model.Inventory;
import com.fooddelivery.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    private InventoryRepository repository;
    private InventoryService service;

    @BeforeEach
    void setup() {
        repository = mock(InventoryRepository.class);
        service = new InventoryService(repository);
    }

    @Test
    void shouldReturnInventoryIfExists() {
        Inventory inv = new Inventory(1L, 2L, 15);
        when(repository.findByProductId(2L)).thenReturn(Optional.of(inv));

        Optional<Inventory> found = service.getInventoryByProductId(2L);

        assertTrue(found.isPresent());
        assertEquals(15, found.get().getQuantity());
    }

    @Test
    void shouldUpdateInventory() {
        when(repository.findByProductId(3L)).thenReturn(Optional.empty());
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        Inventory updated = service.updateInventory(3L, 30);

        assertEquals(30, updated.getQuantity());
        assertEquals(3L, updated.getProductId());
    }
}