package com.example.demo.contollers;

import com.example.demo.dto.RentDto;
import com.example.demo.entities.Rent;
import com.example.demo.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/rent")
public class RentController {
    private final RentService service;

    @GetMapping("/{id}")
    public ResponseEntity<RentDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(new RentDto(service.getRentById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Rent>> getClients(){
        return new ResponseEntity<>(service.getRents(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<RentDto> addClient(@RequestBody RentDto rentDto){
        return new ResponseEntity<>(new RentDto(service.addRent(rentDto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RentDto> updateClient(@RequestBody RentDto rentDto){
        return new ResponseEntity<>(new RentDto(service.updateRent(rentDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

}
