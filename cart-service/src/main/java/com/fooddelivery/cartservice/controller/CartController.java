package com.fooddelivery.cartservice.controller;

import com.fooddelivery.cartservice.model.CartItem;
import com.fooddelivery.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartItem> addItem(@RequestBody CartItem item) {
        return ResponseEntity.ok(cartService.addItem(item));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
