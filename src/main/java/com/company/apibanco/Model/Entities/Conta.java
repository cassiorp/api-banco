package com.company.apibanco.Model.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "contas")
public class Conta {

    @Id
    private String id;
    private String numero;
    private String agencia;
    private Double saldo;

    public Conta(String numero, String agencia) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = 0.0;
    }

}
