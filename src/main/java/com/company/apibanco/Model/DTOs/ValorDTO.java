package com.company.apibanco.Model.DTOs;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ValorDTO {

    @NotNull
    private Double valor;
}
