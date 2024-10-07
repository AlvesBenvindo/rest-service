package com.n2bs.rest_service.service.como_cliente;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FSTT {

    @Bean
    public RestTemplate restTemplateFSTT(RestTemplateBuilder builderFSTT) {
        return builderFSTT.build();
    }

}
