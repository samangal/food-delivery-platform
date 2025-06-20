package com.fooddelivery.orderservice.messaging;

import com.fooddelivery.orderservice.dto.OrderNotification;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.AmqpException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    public OrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Retryable(
            value = { AmqpException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
        )

    public void sendOrderNotification(OrderNotification notification) {
    	try {
    		rabbitTemplate.convertAndSend(queue, notification);
    		log.info("✅ Notification sent to queue '{}': {}", queue, notification);
    	}catch(AmqpException  e) {
    		log.error("❌ Failed to send notification to RabbitMQ: {}", e.getMessage());
            throw e; // So @Retryable can trigger
    	}
        
        
    }
}
