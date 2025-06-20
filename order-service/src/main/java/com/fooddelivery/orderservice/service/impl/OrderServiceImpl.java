package com.fooddelivery.orderservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.dto.OrderResponse;
import com.fooddelivery.orderservice.entity.Order;
import com.fooddelivery.orderservice.repository.OrderRepository;
import com.fooddelivery.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepo;

    public OrderServiceImpl(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

	@Override
	public OrderResponse placeOrder(OrderRequest request) {
		Order order = Order.builder()
                .userId(request.getUserId())
                .totalAmount(request.getTotalAmount())
                .status("PLACED")
                .build();

        Order saved = orderRepo.save(order);
        return new OrderResponse(saved.getId(), saved.getStatus(), "Order placed successfully");
	}

	@Override
	public OrderResponse getOrderById(Long id) {
		Order order = orderRepo.findById(id).orElseThrow();
        return new OrderResponse(order.getId(), order.getStatus(), "Order fetched");
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		return orderRepo.findAll().stream()
				.map(o-> new OrderResponse(o.getId(), o.getStatus(), "" )).toList();
	}
    
    

}
