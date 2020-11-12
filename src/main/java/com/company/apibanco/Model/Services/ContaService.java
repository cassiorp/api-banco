package com.company.apibanco.Model.Services;

import com.company.apibanco.Model.DTOs.ContaDTO;
import com.company.apibanco.Model.DTOs.ValorDTO;
import com.company.apibanco.Model.Entities.Cliente;
import com.company.apibanco.Model.Entities.Conta;
import com.company.apibanco.Model.Exceptions.ClienteComContaException;
import com.company.apibanco.Model.Exceptions.SaldoInsuficienteException;
import com.company.apibanco.Model.Exceptions.ValorInvalidoException;
import com.company.apibanco.Model.Repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteService clienteService;

    public Conta save(ContaDTO contaDTO, String idCliente){
        Cliente cliente = clienteService.find(idCliente);
        System.out.println(cliente.getConta());
        if(cliente.getConta() != null){
            throw new ClienteComContaException("Cliente ja possui conta");
        }
        Conta conta = new Conta(contaDTO.getNumero(), contaDTO.getAgencia());
        contaRepository.save(conta);
        this.clienteService.addConta(cliente, conta);
        return conta;
    }

    public Double saque(String idCliente, ValorDTO valorDTO){
        Cliente cliente = this.clienteService.find(idCliente);
        if(valorDTO.getValor() < cliente.getConta().getSaldo()){
            throw  new SaldoInsuficienteException("Saldo insuficiente");
        }
        Conta conta = cliente.getConta();
        conta.setSaldo(conta.getSaldo() - valorDTO.getValor());
        this.contaRepository.save(conta);
        return conta.getSaldo();
    }

    public Double deposito(String idCliente, Double valor){
        Cliente cliente = this.clienteService.find(idCliente);
        if(valor <= 0){
            throw new ValorInvalidoException("Valor invalido");
        }
        Conta conta = cliente.getConta();
        conta.setSaldo(conta.getSaldo() + valor);
        this.contaRepository.save(conta);
        return conta.getSaldo();
    }
}
