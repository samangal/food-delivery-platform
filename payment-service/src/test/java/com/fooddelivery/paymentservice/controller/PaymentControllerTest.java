package com.fooddelivery.paymentservice.controller;

import com.fooddelivery.paymentservice.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void testCreateAndGetPayment() throws Exception {

        
        String paymentJson = """
                {
                    "orderId": 1, "amount": 99.99, "status": "SUCCESS"
                }
                """;
        mockMvc.perform(post("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(paymentJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/payments/1"))
                .andExpect(status().isOk());
    }
}