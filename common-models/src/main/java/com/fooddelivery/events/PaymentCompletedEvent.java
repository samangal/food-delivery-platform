package com.fooddelivery.events;

public class PaymentCompletedEvent {
	
    private Long orderId;
    private boolean success;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public PaymentCompletedEvent(Long orderId, boolean success) {
		super();
		this.orderId = orderId;
		this.success = success;
	}
	public PaymentCompletedEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaymentCompletedEvent [orderId=" + orderId + ", success=" + success + "]";
	}
    
    

}
