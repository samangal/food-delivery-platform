package com.fooddelivery.cartservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.fooddelivery.cartservice.service.CartService;

@SpringBootApplication
public class CartServiceApplication {

	

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(CartServiceApplication.class, args);
		CartService cartService = context.getBean(CartService.class);
		
		System.out.println("List ALL "+ cartService.list());
	
	}

}
