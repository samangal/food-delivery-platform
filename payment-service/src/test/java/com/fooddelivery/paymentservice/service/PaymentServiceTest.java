package com.fooddelivery.paymentservice.service;

import com.fooddelivery.paymentservice.model.Payment;
import com.fooddelivery.paymentservice.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        paymentService = new PaymentService(paymentRepository);
    }

    @Test
    void testSavePayment() {
    
        Payment payment = new Payment(1L, "1L", new BigDecimal(99.99), "SUCCESS");
        when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(payment);

        Payment saved = paymentService.processPayment(payment);
        assertEquals("SUCCESS", saved.getStatus());
    }
}