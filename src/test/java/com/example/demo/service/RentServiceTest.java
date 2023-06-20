package com.example.demo.service;

import com.example.demo.entities.Car;
import com.example.demo.entities.Client;
import com.example.demo.entities.Rent;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RentRepository;
import com.example.demo.service.serviceImpl.RentServiceImpl;
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
class RentServiceTest {

    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ClientRepository clientRepository;


    Client createClient() {
        Client client = new Client();
        client.setId(2L);
        client.setUsername("Ivan");
        client.setName("Ivan");
        client.setPassport("765567");
        client.setDriverLicense("456190");
        client.setPassword("qwerty");
        client.setBirthday(LocalDate.of(2002, 06, 15));

        return client;
    }

    Car createCar() {
        Car car = new Car();
        car.setMark("Mercedes");
        car.setModel("e-class");
        car.setId(2L);
        car.setPrice(15);
        car.setYearOfRelease(LocalDate.of(2023, 02, 19));
        return car;
    }

    @Test
    void testAddRent() {
        Rent rent = new Rent();
        rent.setId(10L);
        rent.setDateFinishRent(LocalDate.of(2002, 06, 15));
        rent.setDateStartRent(LocalDate.of(2002, 05, 15));
        rent.setClient(createClient());
        rent.setCar(createCar());

        rent = rentRepository.save(rent);

        assertNotNull(rent.getId());

        assertEquals(10L, rent.getId());
        assertEquals(LocalDate.of(2002, 06, 15), rent.getDateFinishRent());
        assertEquals(LocalDate.of(2002, 05, 15), rent.getDateStartRent());
        assertEquals(createClient(), rent.getClient());
        assertEquals(createCar(), rent.getCar());
    }

    @Test
    void testUpdateRent() {
        Rent rent = new Rent();
        rent.setId(10L);
        rent.setDateFinishRent(LocalDate.of(2002, 06, 15));
        rent.setDateStartRent(LocalDate.of(2002, 02, 10));
        rent.setClient(createClient());
        rent.setCar(createCar());

        Rent updatedRent = rentRepository.save(rent);

        assertEquals(10L, rent.getId());
        assertEquals(LocalDate.of(2002, 06, 15), rent.getDateFinishRent());
        assertEquals(LocalDate.of(2002, 02, 10), rent.getDateStartRent());
        assertEquals(createClient(), rent.getClient());
        assertEquals(createCar(), rent.getCar());
    }

    @Test
    void testGetAllRents() {
        List<Rent> rents = rentRepository.findAll();
        assertFalse(rents.isEmpty());
    }

    @Test
    void testGetRentById() {
        Long rentId = 10L;
        Rent rent = new Rent();
        rent.setId(10L);
        rent.setDateFinishRent(LocalDate.of(2002, 06, 15));
        rent.setDateStartRent(LocalDate.of(2002, 02, 10));
        rent.setClient(createClient());
        rent.setCar(createCar());

        RentRepository rentRepository = mock(RentRepository.class);
        when(rentRepository.findById(rentId)).thenReturn(Optional.of(rent));

        RentServiceImpl rentService = new RentServiceImpl(rentRepository,carRepository,clientRepository);

        Rent actualRent = rentService.getRentById(rentId); // Изменено на getClientById

        assertNotNull(actualRent);
        assertEquals(rent.getId(), actualRent.getId());
        assertEquals(rent.getDateStartRent(), actualRent.getDateStartRent());
        assertEquals(rent.getDateFinishRent(), actualRent.getDateFinishRent());
        assertEquals(rent.getClient(), actualRent.getClient());
        assertEquals(rent.getCar(), actualRent.getCar());
    }

    @Test
    void testDeleteRent() {
        Rent rent = new Rent();
        rent.setId(10L);

        rentRepository.deleteById(rent.getId());

        assertFalse(rentRepository.existsById(rent.getId()));
    }
}
