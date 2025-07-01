package com.fooddelivery.notificationservice.listener;

import org.springframework.stereotype.Service;

import com.fooddelivery.events.OrderCreatedEvent;

@Service
public class OrderEventListener {
	
	 public void handleOrderCreatedEvent(OrderCreatedEvent event) {
	        // Add your business logic
	        System.out.println("Received OrderCreatedEvent: " + event);
	    }

}
