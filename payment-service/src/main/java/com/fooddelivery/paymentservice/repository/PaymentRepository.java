package com.fooddelivery.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fooddelivery.paymentservice.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
