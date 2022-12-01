package com.example.parceldelivery1.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(10000)).setReadTimeout(Duration.ofSeconds(10000)).build();
    }

    @Bean
    public WebClient.Builder getWebClient() {
        return WebClient.builder();
    }
}
