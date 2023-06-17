package com.example.demo.contollers;

import com.example.demo.dto.CarDto;
import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(new ClientDto(service.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ClientDto> getByLogin(@PathVariable String login) {
        return new ResponseEntity<>(new ClientDto(service.getByLogin(login)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(new ClientDto(service.addClient(clientDto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(new ClientDto(service.updateClient(clientDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

}
