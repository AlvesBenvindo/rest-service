package com.n2bs.rest_service.service.como_cliente;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Portal {

    @Bean
    public RestTemplate restTemplatePortal(RestTemplateBuilder builderPortal) {
        return builderPortal.build();
    }

}
