package com.fooddelivery.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderNotification implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4906862864035398239L;
	private Long orderId;
    private String message;
}
