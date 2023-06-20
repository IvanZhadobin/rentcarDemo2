package com.example.demo.service;

import com.example.demo.dto.ClientDto;
import com.example.demo.entities.Client;
import org.springframework.stereotype.Service;


@Service
public interface ClientService {
    Client getById(Long id);

    Client getByUsername(String client);
    Client update(ClientDto clientDto);

    Client add(ClientDto clientDto);
    void deleteById(Long id);
}
