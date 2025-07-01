package com.fooddelivery.events;

import java.math.BigDecimal;

public class PaymentFailedEvent {

    private String orderId;
    private String paymentStatus; // e.g., "SUCCESS", "FAILED"
    private BigDecimal amount;
    private String reason;

    public PaymentFailedEvent() {}

    public PaymentFailedEvent(String orderId, String paymentStatus, BigDecimal amount, String reason) {
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.reason = reason;
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

    public String getreason() {
        return reason;
    }

    public void setreason(String reason) {
        this.reason = reason;
    }
}
