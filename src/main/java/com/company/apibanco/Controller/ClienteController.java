package com.company.apibanco.Controller;


import com.company.apibanco.Model.DTOs.ClienteDTO;
import com.company.apibanco.Model.DTOs.UpdateSenhaDTO;
import com.company.apibanco.Model.Entities.Cliente;
import com.company.apibanco.Model.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente save(@RequestBody @Valid ClienteDTO clienteDTO){
        return this.clienteService.save(clienteDTO);
    }

    @GetMapping
    @ResponseBody
    public List<Cliente> findAll(@RequestParam(value = "nome", required = false) String nome) {
        if(nome == null)
            return this.clienteService.findAll();
        return this.clienteService.findByNomeStartingWith(nome);
    }

    @GetMapping(value = "/{id}")
    public Cliente find(@PathVariable String id){
        return this.clienteService.find(id);
    }

    @PatchMapping(value = "/novasenha/{id}")
    public Cliente updateSenha(@PathVariable String id, @RequestBody UpdateSenhaDTO updateSenhaDTO){
        return this.clienteService.updateSenha(id, updateSenhaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id){
        this.clienteService.delete(id);
    }

    @PostMapping(value = "/{id}/picture")
    public URI uploadProfilePicture(@RequestParam(name = "file") MultipartFile file, @PathVariable String id) {
        return this.clienteService.uploadFoto(file,id);
    }

}
