package com.company.apibanco.Model.Repositories;

import com.company.apibanco.Model.Entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    List<Cliente> findByNomeStartingWith(String nome);
}
