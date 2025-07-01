package com.fooddelivery.paymentservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fooddelivery.events.OrderCreatedEvent;



@Service
public class OrderEventListener {
	
	@RabbitListener(queues = "${order.created.queue}")
	 public void handleOrderCreatedEvent(OrderCreatedEvent event) {
	        // Add your business logic
	        System.out.println("Received OrderCreatedEvent: " + event);
	    }

}
