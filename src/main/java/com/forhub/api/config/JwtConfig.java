package com.forhub.api.config;


import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${api.security.secret}")
    private String apiSecret;

    @Bean
    public Algorithm jwtAlgorithm() {
        return Algorithm.HMAC256(apiSecret.getBytes());
    }
}
