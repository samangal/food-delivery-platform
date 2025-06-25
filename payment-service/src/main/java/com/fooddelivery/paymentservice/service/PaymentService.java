package com.fooddelivery.paymentservice.service;

import com.fooddelivery.paymentservice.model.Payment;
import com.fooddelivery.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository repository;
    
    

    public PaymentService(PaymentRepository repository) {
		super();
		this.repository = repository;
	}

	public Payment processPayment(Payment payment) {
        payment.setStatus("COMPLETED");
        return repository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }
}
