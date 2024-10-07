package com.n2bs.rest_service.service;

import com.n2bs.rest_service.MyResponseObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class Transportador {

    private static final String urlPortal = "http://192.168.8.102:8000/api/candidatos";
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

            try{
                Map<String, Object> body = new HashMap<>();
                body.put("candidatos", candidatos);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                //headers.setBearerAuth("");

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

                System.out.println("antes de enviar");
                try{
                    ResponseEntity<MyResponseObject> response = this.rtFSTT.postForEntity(urlFSTT, request, MyResponseObject.class);
                    
                    System.out.println("depois de enviar");
                    if (response.getStatusCode()== HttpStatus.OK){

                        int[] listaDeIds = new int[candidatos.length];
                        System.out.println(candidatos.length);
                        for (int i = 0; i < candidatos.length; i++) {
                            listaDeIds[i] = candidatos[i].id;
                        }
                        
                        body = new HashMap<>();
                        body.put("ids", listaDeIds);
                        HttpEntity<Map<String, Object>> request2 = new HttpEntity<>(body, headers);
                        ResponseEntity<MyResponseObject> response2 = this.rtPortal.postForEntity("http://192.168.8.102:8000/api/candidatos/destroyer", request2, MyResponseObject.class);

                        //System.out.println(response2.getBody().getMensagem());
                    }
                } catch (Exception exc) {
                    System.out.println(" Não chegou ao FSTT ");
                }
            } catch (Exception exc) {
                System.out.println("Não chegou ao FSTT"+ exc.getMessage());
            }
        } catch (Exception esc) {
            System.out.println("Não chegou ao portal!!!");
        }
    }

}
