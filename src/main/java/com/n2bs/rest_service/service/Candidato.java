package com.n2bs.rest_service.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Candidato{
        String nomeCompleto;
        String n_identificacao;
        int tipo_identificacao;
        String email;
        String telefone;
        String n_mecanografico;
        int orgao;

        public Candidato (){}
}
