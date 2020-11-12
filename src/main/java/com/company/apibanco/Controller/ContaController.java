package com.company.apibanco.Controller;


import com.company.apibanco.Model.DTOs.ContaDTO;
import com.company.apibanco.Model.DTOs.ValorDTO;
import com.company.apibanco.Model.Entities.Conta;
import com.company.apibanco.Model.Services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping(value = "/{idCliente}")
    public Conta save(@PathVariable String idCliente, @RequestBody ContaDTO contaDTO) {
        return this.contaService.save(idCliente, contaDTO);
    }

    @GetMapping(value = "/{agencia}")
    public List<Conta> findAllByAgencia(@PathVariable String agencia) {
        return this.contaService.findAllByAgencia(agencia);
    }

    @PatchMapping(value = "/deposito/{idCliente}")
    public Double deposito(@PathVariable String idCliente, @RequestBody ValorDTO valorDTO) {
        return this.contaService.deposito(idCliente, valorDTO);
    }

    @PatchMapping(value = "/saque/{id}")
    public Double sacar(@PathVariable String id, @RequestBody ValorDTO valorDTO) {
        return this.contaService.saque(id, valorDTO);
    }

}
