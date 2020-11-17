package com.company.apibanco.Model.Services;

import com.company.apibanco.Model.DTOs.ContaDTO;
import com.company.apibanco.Model.DTOs.ValorDTO;
import com.company.apibanco.Model.Entities.Cliente;
import com.company.apibanco.Model.Entities.Conta;
import com.company.apibanco.Model.Exceptions.*;
import com.company.apibanco.Model.Repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteService clienteService;

    public Conta save(String idCliente, ContaDTO contaDTO) {
        Cliente cliente = clienteService.find(idCliente);
        if(seTemConta(cliente)){
            throw new ClienteComContaException("Cliente ja possui conta");
        }
        Conta conta = new Conta(contaDTO.getNumero(), contaDTO.getAgencia());
        contaRepository.save(conta);
        this.clienteService.addConta(cliente, conta);
        return conta;
    }

    public Conta find(String id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta nao encontrada"));
    }

    public List<Conta> findAllByAgencia(String agencia) {
        return this.contaRepository.findAllByAgencia(agencia);
    }

    public Double deposito(String idCliente, ValorDTO valorDTO) {

        Cliente cliente = this.clienteService.find(idCliente);

        if(!seTemConta(cliente)) throw new ClienteSemContaException("Cliente nao possui conta!");
        if(valorDTO.getValor() <= 0)throw new ValorInvalidoException("Valor invalido!");

        Conta conta = cliente.getConta();
        conta.setSaldo(conta.getSaldo() + valorDTO.getValor());
        cliente.setConta(conta);
        this.contaRepository.save(conta);
        this.clienteService.save(cliente);

        return conta.getSaldo();

    }

    public Double saque(String idCliente, ValorDTO valorDTO) {

        Cliente cliente = this.clienteService.find(idCliente);
        if(!seTemConta(cliente)) throw new ClienteSemContaException("Cliente nao possui conta!");

        Conta conta = cliente.getConta();
        if(valorDTO.getValor() > conta.getSaldo()) throw new SaldoInsuficienteException("Saldo insuficiente");

        conta.setSaldo(conta.getSaldo() - valorDTO.getValor());
        cliente.setConta(conta);
        this.contaRepository.save(conta);
        this.clienteService.save(cliente);

        return conta.getSaldo();
    }

    private Boolean seTemConta(Cliente cliente) {
        return cliente.getConta() == null ? false : true;
    }

}
