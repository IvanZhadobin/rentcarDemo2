package com.example.demo.controllers;

import com.example.demo.dto.CarDto;
import com.example.demo.entities.Car;
import com.example.demo.service.CarService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {
    private final CarService service;

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(new CarDto(service.getCarById(id)), HttpStatus.OK);
    }

    @GetMapping("/views")
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto){
        return new ResponseEntity<>(new CarDto(service.add(carDto)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto){
        return new ResponseEntity<>(new CarDto(service.update(carDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}

