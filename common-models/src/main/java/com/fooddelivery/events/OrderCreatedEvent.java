package com.fooddelivery.events;

import java.math.BigDecimal;

public class OrderCreatedEvent {
	
	  private Long orderId;
	  private String userId;
	  private BigDecimal amount;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	public OrderCreatedEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderCreatedEvent(Long orderId, String userId, BigDecimal amount) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "OrderCreatedEvent [orderId=" + orderId + ", userId=" + userId + ", amount=" + amount + "]";
	}
	  
	  

}
