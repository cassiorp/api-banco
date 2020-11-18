package com.company.apibanco.Controller;


import com.company.apibanco.Model.DTOs.ContaDTO;
import com.company.apibanco.Model.DTOs.ValorDTO;
import com.company.apibanco.Model.Entities.Conta;
import com.company.apibanco.Model.Services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping(value = "/{idCliente}")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta save(@Valid @PathVariable String idCliente, @Valid @RequestBody ContaDTO contaDTO) {
        return this.contaService.save(idCliente, contaDTO);
    }

    @GetMapping(value = "/{agencia}")
    public List<Conta> findAllByAgencia(@PathVariable String agencia) {
        return this.contaService.findAllByAgencia(agencia);
    }

    @PatchMapping(value = "/deposito/{idCliente}")
    public Double deposito(@Valid @PathVariable String idCliente, @Valid @RequestBody ValorDTO valorDTO) {
        return this.contaService.deposito(idCliente, valorDTO);
    }

    @PatchMapping(value = "/saque/{idCliente}")
    public Double sacar(@Valid @PathVariable String idCliente, @Valid @RequestBody ValorDTO valorDTO) {
        return this.contaService.saque(idCliente, valorDTO);
    }

}
