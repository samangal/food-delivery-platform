package com.fooddelivery.cartservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fooddelivery.cartservice.model.CartItem;
import com.fooddelivery.cartservice.repository.CartItemRepository;
import com.fooddelivery.cartservice.service.CartService;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    private CartItemRepository cartRepository;
    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartRepository = mock(CartItemRepository.class);
        cartService = new CartService(cartRepository);
    }

    @Test
    void testAddCart() {
        CartItem cart = new CartItem(1L, "samangal", 1L, 10, new BigDecimal(2.0));
        when(cartRepository.save(cart)).thenReturn(cart);
        CartItem saved = cartService.addItem(cart);
        assertEquals(cart.getProductId(), saved.getProductId());
        verify(cartRepository, times(1)).save(cart);
    }
}
