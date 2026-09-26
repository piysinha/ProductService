package com.scaler.productservice.security;

import com.scaler.productservice.clients.authenticationclient.AuthenticationClient;
import com.scaler.productservice.controllers.ProductController;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepositories;
import com.scaler.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(SpringSecurityConfig.class)
@TestPropertySource(properties = {"ISSUER_URL=https://user-service.example.test", "SERVER_PORT=0"})
class ProductEndpointsSecurityTest {

    private static final String PRODUCT = "{\"title\":\"Pen\",\"price\":10}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "selfProductService")
    private ProductService productService;
    @MockBean
    private ProductRepositories productRepositories;
    @MockBean
    private AuthenticationClient authenticationClient;
    @MockBean
    private JwtDecoder jwtDecoder;

    @Test
    void anyone_can_browse_products() throws Exception {
        mockMvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    void adding_a_product_needs_a_token() throws Exception {
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(PRODUCT))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updating_a_product_needs_a_token() throws Exception {
        mockMvc.perform(patch("/products/1").contentType(MediaType.APPLICATION_JSON).content(PRODUCT))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleting_a_product_needs_a_token() throws Exception {
        mockMvc.perform(delete("/products/1")).andExpect(status().isUnauthorized());
    }

    @Test
    void a_signed_in_client_can_add_a_product() throws Exception {
        when(productRepositories.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/products").with(jwt()).contentType(MediaType.APPLICATION_JSON).content(PRODUCT))
                .andExpect(status().isOk());
    }
}
