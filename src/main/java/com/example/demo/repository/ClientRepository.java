package com.example.demo.repository;

import com.example.demo.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAll();
    Optional<Client> findByUsername(String username);
}
