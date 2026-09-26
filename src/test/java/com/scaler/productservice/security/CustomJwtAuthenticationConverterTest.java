package com.scaler.productservice.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomJwtAuthenticationConverterTest {

    private final CustomJwtAuthenticationConverter converter = new CustomJwtAuthenticationConverter();

    @Test
    void roles_in_the_token_become_authorities() {
        Jwt jwt = Jwt.withTokenValue("token").header("alg", "RS256").subject("7").claim("roles", List.of("ADMIN")).build();

        assertThat(converter.convert(jwt).getAuthorities()).extracting(GrantedAuthority::getAuthority).contains("ADMIN");
    }

    @Test
    void a_token_without_roles_has_no_role_authorities() {
        Jwt jwt = Jwt.withTokenValue("token").header("alg", "RS256").subject("7").claim("scope", "read").build();

        assertThat(converter.convert(jwt).getAuthorities()).extracting(GrantedAuthority::getAuthority).containsExactly("SCOPE_read");
    }
}
