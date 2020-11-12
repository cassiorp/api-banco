package com.company.apibanco.Model.DTOs;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateSenhaDTO {
    @NotNull
    @NotEmpty
    private String senha;
}
