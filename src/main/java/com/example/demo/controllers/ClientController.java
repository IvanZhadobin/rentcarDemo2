package com.example.demo.controllers;

import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Validated
public class ClientController {

    private final ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(new ClientDto(service.getById(id)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(new ClientDto(service.add(clientDto)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(new ClientDto(service.update(clientDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

}
