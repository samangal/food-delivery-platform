package com.fooddelivery.orderservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.events.PaymentFailedEvent;
import com.fooddelivery.orderservice.service.OrderService;

@Service
public class OrderServiceListener {
	
	@Autowired
	private OrderService orderService;
	
	 @RabbitListener(queues = "${payment.rabbitmq.failure.queue}")
	    public void handlePaymentFailed(PaymentFailedEvent evt) {
	        // Compensate: cancel order, refund inventory, notify user, etc.
	        orderService.cancelOrder(evt.getOrderId(), evt.getreason());
	    }

}
