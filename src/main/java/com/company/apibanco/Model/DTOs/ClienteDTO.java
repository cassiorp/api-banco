package com.company.apibanco.Model.DTOs;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClienteDTO {

    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    //@CPF
    private String cpf;
    @NotNull
    @NotEmpty
    private String senha;



}
