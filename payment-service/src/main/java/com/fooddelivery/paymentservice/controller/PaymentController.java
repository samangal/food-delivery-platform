package com.fooddelivery.paymentservice.controller;

import com.fooddelivery.paymentservice.model.Payment;
import com.fooddelivery.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping
    public Payment processPayment(@RequestBody Payment payment) {
        return service.processPayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return service.getAllPayments();
    }
}
