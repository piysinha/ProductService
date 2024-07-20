package com.scaler.productservice.clients.authenticationclient;

import com.scaler.productservice.clients.authenticationclient.dtos.ValidateResponseDto;
import com.scaler.productservice.clients.authenticationclient.dtos.ValidateTokenRequestDto;
import com.scaler.productservice.clients.fakeStoreApi.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class AuthenticationClient {

    RestTemplateBuilder restTemplateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ValidateResponseDto validate(String token, Long userId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDto request = new ValidateTokenRequestDto();

        request.setToken(token);
        request.setUserId(userId);

        ResponseEntity<ValidateResponseDto> response = restTemplate.postForEntity(
                "http://localhost:9000/auth/Validate",
                request,
                ValidateResponseDto.class);

        return response.getBody();
    }
}
