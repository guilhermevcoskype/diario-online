package com.gui.diarioOnline.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${igdb.client-id}")
    private String clientId;

    @Value("${igdb.token}")
    private String token;

    @Bean
    public WebClient igdbClient() {
        return WebClient.builder()
                .baseUrl("https://api.igdb.com/v4")
                .defaultHeader("Client-ID", clientId)
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }
}
