package com.fooddelivery.orderservice.controller;

import com.fooddelivery.orderservice.dto.OrderNotification;
import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.dto.OrderResponse;
import com.fooddelivery.orderservice.entity.Order;
import com.fooddelivery.orderservice.messaging.OrderMessagingService;
import com.fooddelivery.orderservice.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order API", description = "Manage orders")

public class OrderController {

    private final OrderService orderService;
    @Autowired
    private OrderMessagingService messagingService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
   

    @Operation(summary = "Place a new order")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        
    	OrderResponse response = orderService.placeOrder(orderRequest);

        // Build and send a notification message
        OrderNotification notification = new OrderNotification(
            response.getOrderId(),
            "Order placed successfully with status: " + response.getStatus()
        );

        messagingService.sendOrderNotification(notification);

        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Get order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Get all orders")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
