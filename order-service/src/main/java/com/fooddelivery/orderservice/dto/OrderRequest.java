package com.fooddelivery.orderservice.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderRequest {
	
	private String userId;
    private List<Long> productIds;
    private Double totalAmount;

}
