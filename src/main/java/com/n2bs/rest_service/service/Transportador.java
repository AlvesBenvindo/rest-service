package com.n2bs.rest_service.service;

import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.n2bs.rest_service.MyResponseObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class Transportador {

    private static final String urlPortal = "http://localhost/OlimpoWeb/public/api/candidatos";
    private static final String urlFSTT = "http://localhost/OlimpoWeb/public/api/candidatos";
    private RestTemplate rtPortal;
    private RestTemplate rtFSTT;

    public Transportador (RestTemplate restTemplatePortal, RestTemplate restTemplateFSTT) {
        this.rtPortal = restTemplatePortal;
        this.rtFSTT = restTemplateFSTT;
    }

    @Scheduled(fixedRate = 5000)
    public void carregarCandidatos () {
        try{
            Candidato[] candidatos = this.rtPortal.getForObject(urlPortal, Candidato[].class);

            /*for (int i = 0; i < 4; i++) {
                System.out.println(candidatos[i].getNomeCompleto());
                System.out.println(candidatos[i].getEmail());
            }*/

            try{
                Map<String, Object> body = new HashMap<>();
                body.put("candidatos", candidatos);

                HttpHeaders headers = new HttpHeaders();
                //headers.set("Authorization", "Bearer Aqui terá o token");
                headers.setContentType(MediaType.APPLICATION_JSON);
                //headers.setBearerAuth("");

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

                System.out.println("antes de enviar");
                ResponseEntity<MyResponseObject> response = this.rtFSTT.postForEntity(urlFSTT, request, MyResponseObject.class);
                System.out.println("depois de enviar");
                if (response.getStatusCode()== HttpStatus.OK){
                    System.out.println(response.getBody().getMensagem());
                }
            } catch (Exception exc) {
                System.out.println("Não chegou ao FSTT");
            }
        } catch (Exception esc) {
            System.out.println("Não chegou ao portal!!!");
        }
    }

}
