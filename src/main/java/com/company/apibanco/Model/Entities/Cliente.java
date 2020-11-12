package com.company.apibanco.Model.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private Conta conta;

    public Cliente(String nome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }
    
}
