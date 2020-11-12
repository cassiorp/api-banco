package com.company.apibanco.Model.Repositories;

import com.company.apibanco.Model.Entities.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends MongoRepository<Conta, String> {
    List<Conta> findAllByAgencia(String agencia);
}
