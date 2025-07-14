package com.fooddelivery.cartservice.service;

import com.fooddelivery.cartservice.model.CartItem;
import com.fooddelivery.cartservice.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem addItem(CartItem item) {
        return cartItemRepository.save(item);
    }
    
    public List<CartItem> list() {
        return cartItemRepository.findAll();
    }

    public List<CartItem> getCartItems(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void removeItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart(String userId) {
        List<CartItem> items = cartItemRepository.findByUserId(userId);
        cartItemRepository.deleteAll(items);
    }
}
