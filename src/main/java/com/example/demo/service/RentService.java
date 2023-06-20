package com.example.demo.service;

import com.example.demo.dto.RentDto;
import com.example.demo.entities.Rent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentService {
    Rent getRentById(Long id);

    List<Rent> getRents();

    Rent add(RentDto rentDto);

    Rent update(RentDto rentDto);

    void deleteById(Long id);
}
