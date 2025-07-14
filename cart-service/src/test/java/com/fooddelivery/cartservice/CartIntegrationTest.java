package com.fooddelivery.cartservice;

import com.fooddelivery.cartservice.repository.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CartServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
		  "spring.flyway.enabled=false",
		  "spring.datasource.url=jdbc:postgresql://localhost:5432/cartdb",
		  "spring.datasource.username=postgres",
		  "spring.datasource.password=password",
		  "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect"

		})
public class CartIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartItemRepository cartRepository;

    @Test
    void testAddCartEndpoint() throws Exception {
        String cartJson = "{\"userId\":101,\"productId\":1,\"quantity\":2}";

        mockMvc.perform(post("/api/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cartJson))
                .andExpect(status().isOk());
    }
}
