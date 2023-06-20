package com.example.demo.service.serviceImpl;

import com.example.demo.dto.ClientDto;
import com.example.demo.entities.Client;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Клиент с данным id:" + id + " не найден!"));
    }

    @Override
    public Client getByUsername(String username) {
        return clientRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Профиль с логином " + username + " не найден!"));
    }
    @Override
    public Client add(ClientDto clientDto) {
        if (clientRepository.existsById(clientDto.getId())) {
            throw new ResourceAlreadyExistsException("Клиент с таким id уже существует");
        }
        Client client = new Client();
        client.setId(clientDto.getId());

        initClientFromDto(client, clientDto);
        clientRepository.save(client);
        return client;
    }
    @Override
    public Client update(ClientDto clientDto) {
        Client client = clientRepository.findById(clientDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Клиент с id = " + clientDto.getId() + " не существует"));
        initClientFromDto(client, clientDto);
        clientRepository.save(client);
        return client;
    }
    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public void initClientFromDto(Client client, ClientDto clientDto) {
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setUsername(clientDto.getUsername());
        client.setBirthday(clientDto.getBirthday());
        client.setPassport(clientDto.getPassport());
        client.setPassword(clientDto.getPassword());
        client.setDriverLicense(clientDto.getDriverLicense());
    }
}
