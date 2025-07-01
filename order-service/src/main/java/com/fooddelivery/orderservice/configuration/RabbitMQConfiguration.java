package com.fooddelivery.orderservice.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfiguration {
	
		@Value("${rabbitmq.order.exchange}")
	    private String exchange;

		@Value("${rabbitmq.order.routing-key}")
	    private String routingKey;

	    @Bean
	    public TopicExchange orderExchange() {
	        return new TopicExchange(exchange);
	    }

	    @Bean
	    public Queue orderQueue() {
	        return new Queue("order.created.queue");
	    }

	    @Bean
	    public Binding binding() {
	        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(routingKey);
	    }

}
