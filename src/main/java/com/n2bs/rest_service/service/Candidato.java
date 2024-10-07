package com.n2bs.rest_service.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Candidato{
        protected int id;
        protected String nome;
        protected String n_identificacao;
        protected int tipo_documento;
        protected String email;
        protected String telefone;
        protected String n_mecanografico;
        protected int orgao_id;
        protected String arquivo_identificacao;
        protected String arquivo_declaracao;

        public Candidato (){}
}
