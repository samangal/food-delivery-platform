package com.fooddelivery.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class OrderResponse {
	
	private Long orderId;
    private String status;
    private String message;

}
