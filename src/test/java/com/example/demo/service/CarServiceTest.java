package com.example.demo.service;

import com.example.demo.entities.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.serviceImpl.CarServiceImpl;
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
class CarServiceTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarServiceImpl carService;

    @Test
    void testAddCar() {
        Car car = new Car();
        car.setMark("Mercedes");
        car.setModel("e-class");
        car.setId(10L);
        car.setPrice(15);
        car.setYearOfRelease(LocalDate.of(2023,02,19));

        car = carRepository.save(car);

        assertNotNull(car.getId());

        assertEquals(10L, car.getId());
        assertEquals("Mercedes", car.getMark());
        assertEquals("e-class", car.getModel());
        assertEquals(15, car.getPrice());
        assertEquals(LocalDate.of(2023,02,19), car.getYearOfRelease());
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setId(10L);
        car.setMark("Mercedes");
        car.setModel("e-class");
        car.setPrice(25);
        car.setYearOfRelease(LocalDate.of(2023,02,19));

        Car updatedCar = carRepository.save(car);

        assertEquals(10L, car.getId());
        assertEquals("Mercedes", car.getMark());
        assertEquals("e-class", car.getModel());
        assertEquals(25, car.getPrice());
        assertEquals(LocalDate.of(2023,02,19), car.getYearOfRelease());
    }

    @Test
    void testGetAllCars() {
        List<Car> cars = carRepository.findAll();

        assertFalse(cars.isEmpty());
    }

    @Test
    void testGetItemById() {
        Long itemId = 10L;
        Car expectedItem = new Car();
        expectedItem.setId(itemId);
        expectedItem.setModel("e-class");
        expectedItem.setMark("Mercedes");
        expectedItem.setPrice(25);
        expectedItem.setYearOfRelease(LocalDate.of(2023,02,19));

        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.findById(itemId)).thenReturn(Optional.of(expectedItem));

        CarServiceImpl carService = new CarServiceImpl(carRepository);

        Car actualItem = carService.getCarById(itemId);

        assertNotNull(actualItem);
        assertEquals(expectedItem.getId(), actualItem.getId());
        assertEquals(expectedItem.getModel(), actualItem.getModel());
        assertEquals(expectedItem.getMark(), actualItem.getMark());
        assertEquals(expectedItem.getPrice(), actualItem.getPrice());
        assertEquals(expectedItem.getYearOfRelease(), actualItem.getYearOfRelease());
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setId(10L);

        carRepository.deleteById(car.getId());

        assertFalse(carRepository.existsById(car.getId()));
    }
}
