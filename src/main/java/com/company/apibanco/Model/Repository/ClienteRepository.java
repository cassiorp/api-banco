package com.company.apibanco.Model.Repository;

import com.company.apibanco.Model.Entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Cliente findByNome(String nome);
}
