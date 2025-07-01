package com.fooddelivery.events;

import java.math.BigDecimal;

public class PaymentProcessedEvent {

    private String orderId;
    private String paymentStatus; // e.g., "SUCCESS", "FAILED"
    private BigDecimal amount;
    private String message;

    public PaymentProcessedEvent() {}

    public PaymentProcessedEvent(String orderId, String paymentStatus, BigDecimal amount, String message) {
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.message = message;
    }

    // Getters and Setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
