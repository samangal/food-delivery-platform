package com.fooddelivery.productservice.service;

import com.fooddelivery.productservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

}
