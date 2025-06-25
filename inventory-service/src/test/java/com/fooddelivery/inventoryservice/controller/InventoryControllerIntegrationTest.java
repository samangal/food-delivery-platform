package com.fooddelivery.inventoryservice.controller;

import com.fooddelivery.inventoryservice.model.Inventory;
import com.fooddelivery.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventoryRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        repository.save(new Inventory(null, 1L, 10));
    }

    @Test
    void shouldReturnInventoryByProductId() throws Exception {
        mockMvc.perform(get("/api/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(1)))
                .andExpect(jsonPath("$.quantity", is(10)));
    }

    @Test
    void shouldUpdateInventoryQuantity() throws Exception {
        mockMvc.perform(put("/api/inventory/1?quantity=20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity", is(20)));
    }
}