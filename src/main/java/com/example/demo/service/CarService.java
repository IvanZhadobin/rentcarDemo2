package com.example.demo.service;

import com.example.demo.dto.CarDto;
import com.example.demo.entities.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Car getCarById(Long id);

    List<Car> getCars();

    Car add(CarDto car);

    Car update(CarDto car);

    void deleteById(Long id);
}
