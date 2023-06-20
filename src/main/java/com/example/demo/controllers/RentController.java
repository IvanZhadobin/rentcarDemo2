package com.example.demo.controllers;

import com.example.demo.dto.RentDto;
import com.example.demo.entities.Rent;
import com.example.demo.service.RentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
@Validated
public class RentController {
    private final RentService service;

    @GetMapping("/{id}")
    public ResponseEntity<RentDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(new RentDto(service.getRentById(id)), HttpStatus.OK);
    }

    @GetMapping("/views")
    public ResponseEntity<List<Rent>> getRents(){
        return new ResponseEntity<>(service.getRents(), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<RentDto> addRent(@RequestBody RentDto rentDto){
        return new ResponseEntity<>(new RentDto(service.add(rentDto)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentDto){
        return new ResponseEntity<>(new RentDto(service.update(rentDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

}
