package com.company.apibanco.Model.Services;


import com.company.apibanco.Model.DTOs.ClienteDTO;
import com.company.apibanco.Model.DTOs.UpdateSenhaDTO;
import com.company.apibanco.Model.Entities.Cliente;
import com.company.apibanco.Model.Entities.Conta;
import com.company.apibanco.Model.Exceptions.ClienteNaoEncontradoException;
import com.company.apibanco.Model.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(ClienteDTO clienteDTO) {
        return this.clienteRepository.save(new Cliente(clienteDTO.getNome(), clienteDTO.getEmail(),
                clienteDTO.getCpf(), clienteDTO.getSenha()));
    }

    public Cliente save(Cliente cliente){
        return this.clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    public Cliente find(String id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente nao encontrado"));
    }

    public List<Cliente> findByNomeStartingWith(String nome) {
        return clienteRepository.findByNomeStartingWith(nome);
    }

    public Cliente addConta(Cliente cliente, Conta conta) {
        cliente.setConta(conta);
        return this.clienteRepository.save(cliente);
    }

    public Cliente updateSenha(String id, UpdateSenhaDTO updateSenhaDTO) {
        Cliente cliente = this.find(id);
        cliente.setSenha(updateSenhaDTO.getSenha());
        return this.clienteRepository.save(cliente);
    }

    public void delete(String id) {
        this.clienteRepository.deleteById(id);
    }

}
