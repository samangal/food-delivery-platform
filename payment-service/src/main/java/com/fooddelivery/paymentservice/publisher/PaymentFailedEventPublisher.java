package com.fooddelivery.paymentservice.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fooddelivery.events.PaymentFailedEvent;
import com.fooddelivery.events.PaymentProcessedEvent;

@Service
public class PaymentFailedEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    public PaymentFailedEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishPaymentEvent(PaymentFailedEvent paymentFailedEvent) {
        rabbitTemplate.convertAndSend(exchange, routingKey, paymentFailedEvent);
    }
}
