package com.fooddelivery.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.productservice.model.Product;
import com.fooddelivery.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIT {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product product = new Product(null, "Mango", "Juicy mango",  new BigDecimal(15.0), 50);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Mango"));
    }

    @Test
    void shouldReturnAllProducts() throws Exception {
        Product product = new Product(null, "Banana", "Yellow banana",  new BigDecimal(5.0), 100);
        productRepository.save(product);

        mockMvc.perform(get("/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name").value("Banana"));
    }

    @Test
    void shouldReturnProductById() throws Exception {
        Product saved = productRepository.save(new Product(null, "Orange", "Citrus",  new BigDecimal(8.0), 20));

        mockMvc.perform(get("/products/" + saved.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Orange"));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        Product saved = productRepository.save(new Product(null, "Grapes", "Green grapes",  new BigDecimal(12.0), 30));
        saved.setPrice( new BigDecimal(14.0));

        mockMvc.perform(put("/products/" + saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saved)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(14.0));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        Product saved = productRepository.save(new Product(null, "Pear", "Sweet pear", new BigDecimal(5.0), 15));

        mockMvc.perform(delete("/products/" + saved.getId()))
            .andExpect(status().isNoContent());
    }
    
    @Test
    void shouldReturnProductsByPriceRange() throws Exception {
        Product product = new Product(null, "Tablet", "Android Tablet", new BigDecimal("250.00"), 5);
        productRepository.save(product);

        mockMvc.perform(get("/products/price-range")
                .param("min", "200")
                .param("max", "300"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Tablet"));
    }

}
