package com.fooddelivery.paymentservice.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfiguration {
	
		@Value("${rabbitmq.payment.exchange}")
	    private String exchange;

		@Value("${rabbitmq.payment.routing-key}")
	    private String routingKey;
		
		@Value("${rabbitmq.payment.queue}")
	    private String queue;
		
		@Value("${rabbitmq.payment_failed.exchange}")
	    private String exchangeFailed;

		@Value("${rabbitmq.payment_failed.routing-key}")
	    private String routingKey_failed;
		
		@Value("${rabbitmq.payment_failed.queue}")
	    private String queue_failed;

	    @Bean
	    public TopicExchange paymentExchange() {
	        return new TopicExchange(exchange);
	    }

	    @Bean
	    public Queue paymentQueue() {
	        return new Queue(queue);
	    }

	    @Bean
	    public Binding binding() {
	        return BindingBuilder.bind(paymentQueue()).to(paymentExchange()).with(routingKey);
	    }
	    
	    @Bean
	    public TopicExchange paymentFailedExchange() {
	        return new TopicExchange(exchangeFailed);
	    }

	    @Bean
	    public Queue paymentFailedQueue() {
	        return new Queue(queue_failed);
	    }

	    @Bean
	    public Binding bindingForFailed() {
	        return BindingBuilder.bind(paymentFailedQueue()).to(paymentFailedExchange()).with(routingKey_failed);
	    }

}
