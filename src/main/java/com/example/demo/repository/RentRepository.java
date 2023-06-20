package com.example.demo.repository;

import com.example.demo.entities.Car;
import com.example.demo.entities.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
    List<Rent> findAll();
}
