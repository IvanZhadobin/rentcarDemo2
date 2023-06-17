package com.example.demo.contollers;

import com.example.demo.dto.CarDto;
import com.example.demo.entities.Car;
import com.example.demo.service.CarService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/car")
@Api(value="Car controller", description = "crud operations")
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
        return new ResponseEntity<>(new CarDto(service.addCar(carDto)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto){
        return new ResponseEntity<>(new CarDto(service.updateCar(carDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}

