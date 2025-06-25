package com.fooddelivery.productservice.service;

import com.fooddelivery.productservice.model.Product;
import com.fooddelivery.productservice.repository.ProductRepository;
import com.fooddelivery.productservice.service.impl.ProductServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductRepository productRepository;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void shouldSaveProduct() {

        Product product = new Product(null, "Apple", "Fresh Red Apple", new BigDecimal(10.0), 100);
        when(productRepository.save(product)).thenReturn(product);

        Product saved = productService.saveProduct(product);
        assertThat(saved).isEqualTo(product);
    }

    @Test
    void shouldReturnAllProducts() {
        List<Product> list = List.of(new Product(1L, "Item", "Desc", new BigDecimal(5.0), 10));
        when(productRepository.findAll()).thenReturn(list);

        List<Product> result = productService.getAllProducts();
        assertThat(result).hasSize(1);
    }

    @Test
    void shouldReturnProductById() {
        Product product = new Product(1L, "Item", "Desc",  new BigDecimal(5.0), 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);
        assertThat(result.getName()).isEqualTo("Item");
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productService.getProductById(2L));
    }

    @Test
    void shouldUpdateProduct() {
        Product existing = new Product(1L, "Old", "Old desc",new BigDecimal(1.0), 5);
        Product updated = new Product(null, "New", "New desc",new BigDecimal(2.0), 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any())).thenReturn(updated);

        Product result = productService.updateProduct(1L, updated);
        assertThat(result.getName()).isEqualTo("New");
    }

    @Test
    void shouldDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
    
    @Test
    void shouldReturnProductsByPriceRange() {
        List<Product> products = List.of(new Product(1L, "Watch", "Smart watch", new BigDecimal("89.99"), 15));
        when(productRepository.findByPriceBetween(new BigDecimal("50"), new BigDecimal("100")))
                .thenReturn(products);

        List<Product> result = productService.getProductsByPriceRange(new BigDecimal("50"), new BigDecimal("100"));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Watch");
    }

}
