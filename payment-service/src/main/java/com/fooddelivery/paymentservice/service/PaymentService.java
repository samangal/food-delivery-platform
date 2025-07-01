package com.fooddelivery.paymentservice.service;

import com.fooddelivery.events.PaymentFailedEvent;
import com.fooddelivery.events.PaymentProcessedEvent;
import com.fooddelivery.paymentservice.model.Payment;
import com.fooddelivery.paymentservice.publisher.PaymentEventPublisher;
import com.fooddelivery.paymentservice.publisher.PaymentFailedEventPublisher;
import com.fooddelivery.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository repository;
    
    @Autowired
    private PaymentEventPublisher paymentEventPublisher;
    @Autowired
    private PaymentFailedEventPublisher paymentFailedEventPublisher;
    

    public PaymentService(PaymentRepository repository) {
		super();
		this.repository = repository;
	}

	public Payment processPayment(Payment payment) {
		
		try {
			 payment.setStatus("COMPLETED");
	         payment = repository.save(payment);
	         
	         
	         paymentEventPublisher.publishPaymentEvent(new PaymentProcessedEvent(
	        		 payment.getOrderId(),
	        		 "SUCCESS",
	        		 payment.getAmount(),
	        		 "Payment processed successfully"
	));
	         return payment;
		}catch(Exception ex) {
			
			paymentFailedEventPublisher.publishPaymentEvent(new PaymentFailedEvent(
			           payment.getOrderId(),
			           "FAILED",
			           payment.getAmount(),
			           "Payement Failed because".concat(ex.getMessage())
			        ));
		}
		return payment;
       
    }



	public List<Payment> getAllPayments() {
        return repository.findAll();
    }
}
