package com.fooddelivery.orderservice.service;

import java.util.List;

import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.dto.OrderResponse;

public interface OrderService {
	
	OrderResponse placeOrder(OrderRequest request);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrders();

}
