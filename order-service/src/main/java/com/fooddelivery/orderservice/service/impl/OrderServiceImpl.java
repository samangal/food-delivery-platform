package com.fooddelivery.orderservice.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fooddelivery.events.OrderCreatedEvent;
import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.dto.OrderResponse;
import com.fooddelivery.orderservice.entity.Order;
import com.fooddelivery.orderservice.repository.OrderRepository;
import com.fooddelivery.orderservice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepo;
	
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.routing-key}")
	private String routingKey;

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
        
        
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(order.getId(), order.getUserId(), new BigDecimal(order.getTotalAmount()));
        rabbitTemplate.convertAndSend(exchange, routingKey, orderCreatedEvent);
        
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

	@Override
	public void cancelOrder(String orderId, String getreason) {
		log.info("ORDER CANCELLED");
		
	}
    
    

}
