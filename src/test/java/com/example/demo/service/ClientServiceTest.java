package com.example.demo.service;

import com.example.demo.entities.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.serviceImpl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceTest {
    @Autowired
    private ClientRepository clientRepository;


    @Test
    void testAddClient() {
        Client client = new Client();
        client.setId(10L);
        client.setUsername("Ivan");
        client.setName("Ivan");
        client.setPassport("765567");
        client.setDriverLicense("456190");
        client.setPassword("qwerty");
        client.setBirthday(LocalDate.of(2002,06,15));

        client = clientRepository.save(client);

        assertNotNull(client.getId());

        assertEquals(10L, client.getId());
        assertEquals("Ivan", client.getUsername());
        assertEquals("Ivan", client.getName());
        assertEquals("765567", client.getPassport());
        assertEquals("456190", client.getDriverLicense());
        assertEquals("qwerty", client.getPassword());
        assertEquals(LocalDate.of(2002,06,15), client.getBirthday());
    }

    @Test
    void testUpdateClient() {
        Client client = new Client();
        client.setId(10L);
        client.setUsername("Ivan");
        client.setName("boss");
        client.setPassport("765567");
        client.setDriverLicense("456190");
        client.setPassword("qwerty");
        client.setBirthday(LocalDate.of(2002,06,15));

        Client updatedClient = clientRepository.save(client);

        assertEquals(10L, client.getId());
        assertEquals("Ivan", client.getUsername());
        assertEquals("boss", client.getName());
        assertEquals("765567", client.getPassport());
        assertEquals("456190", client.getDriverLicense());
        assertEquals("qwerty", client.getPassword());
        assertEquals(LocalDate.of(2002,06,15), client.getBirthday());
    }

    @Test
    void testGetAllClients() {
        List<Client> clients = clientRepository.findAll();
        assertFalse(clients.isEmpty());
    }

    @Test
    void testGetClientById() {
        Long clientId = 10L;
        Client client = new Client();
        client.setUsername("Ivan");
        client.setName("Ivan");
        client.setPassport("765567");
        client.setDriverLicense("456190");
        client.setPassword("qwerty");
        client.setBirthday(LocalDate.of(2002,06,15));

        ClientRepository clientRepository = mock(ClientRepository.class);
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        ClientServiceImpl clientService = new ClientServiceImpl(clientRepository);

        Client actualClient = clientService.getById(clientId);

        assertNotNull(actualClient);
        assertEquals(client.getId(), actualClient.getId());
        assertEquals(client.getName(), actualClient.getName());
        assertEquals(client.getUsername(), actualClient.getUsername());
        assertEquals(client.getPassport(), actualClient.getPassport());
        assertEquals(client.getDriverLicense(), actualClient.getDriverLicense());
        assertEquals(client.getPassword(), actualClient.getPassword());
        assertEquals(client.getPassport(), actualClient.getPassport());
    }

    @Test
    void testDeleteClient() {
        Client client = new Client();
        client.setId(10L);

        clientRepository.deleteById(client.getId());

        assertFalse(clientRepository.existsById(client.getId()));
    }
}
