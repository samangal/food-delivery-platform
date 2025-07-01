package com.fooddelivery.orderservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.entity.Order;
import com.fooddelivery.orderservice.repository.OrderRepository;
import com.fooddelivery.orderservice.service.impl.OrderServiceImpl;

public class OrderServiceImplTest {
	
	
	private final OrderRepository repo = mock(OrderRepository.class);
    private final OrderService service = new OrderServiceImpl(repo);
    
    @Test
    void placeOrder_shouldReturnResponse() {
    	OrderRequest req = new OrderRequest();
        req.setUserId("samangal");
        req.setTotalAmount(100.0);
        
        Order mockOrder = Order.builder()
                .id(1L)
                .userId("samangal")
                .totalAmount(100.0)
                .status("PLACED")
                .build();
        
        when(repo.save(any(Order.class))).thenReturn(mockOrder);
        
        var res = service.placeOrder(req);
        assertEquals(1L, res.getOrderId());
        assertEquals("PLACED", res.getStatus());

    }

    @Test
    void getOrderById_shouldReturnResponse() {
        Order mockOrder = new Order(1L, "samangal", 100.0, "PLACED");
        when(repo.findById(1L)).thenReturn(Optional.of(mockOrder));

        var res = service.getOrderById(1L);
        assertEquals("PLACED", res.getStatus());
    }

}
