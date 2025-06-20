package com.fooddelivery.orderservice.controller;

import com.fooddelivery.orderservice.dto.OrderRequest;
import com.fooddelivery.orderservice.dto.OrderResponse;
import com.fooddelivery.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    @Test
    void testCreateOrder() throws Exception {
        OrderResponse res = new OrderResponse(1L, "PLACED", "Success");
        Mockito.when(service.placeOrder(any(OrderRequest.class))).thenReturn(res);

        mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content("{\"userId\":1,\"productIds\":[101],\"totalAmount\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PLACED"));
    }
}
