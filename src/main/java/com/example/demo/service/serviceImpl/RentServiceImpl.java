package com.example.demo.service.serviceImpl;

import com.example.demo.dto.RentDto;
import com.example.demo.entities.Car;
import com.example.demo.entities.Client;
import com.example.demo.entities.Rent;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RentRepository;
import com.example.demo.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {
    RentRepository rentRepository;
    CarRepository carRepository;

    ClientRepository clientRepository;

    @Override
    public Rent getRentById(Long id) {
        return rentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Аренда с таким id: " + id + " уже существует"));
    }

    @Override
    public List<Rent> getRents() {
        return rentRepository.findAll();
    }

    @Override
    public Rent add(RentDto rentDto) {
        if (rentRepository.existsById(rentDto.getId())) {
            throw new ResourceAlreadyExistsException("Аренда с таким id уже существует");
        }
        Rent rent = new Rent();

        Car updatedCar = carRepository.findById(rentDto.getCarId())
                .orElseThrow(() -> new ResourceNotFoundException("Автомобиля с id = " + rentDto.getCarId() + " не существует"));
        Client updatedClient = clientRepository.findById(rentDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Человека с id = " + rentDto.getClientId() + " не существует"));

        rent.setId(rentDto.getId());
        initRentFromDto(rent, rentDto, updatedClient, updatedCar);

        rentRepository.save(rent);
        return rent;
    }

    @Override
    public Rent update(RentDto rentDto) {
        Rent rent = rentRepository.findById(rentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Аренда с id = " + rentDto.getId() + " не существует"));
        Car updatedCar = carRepository.findById(rentDto.getCarId())
                .orElseThrow(() -> new ResourceNotFoundException("Автомобиля с id = " + rentDto.getCarId() + " не существует"));
        Client updatedClient = clientRepository.findById(rentDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Человека с id = " + rentDto.getClientId() + " не существует"));
        initRentFromDto(rent, rentDto, updatedClient, updatedCar);
        rentRepository.save(rent);
        return rent;
    }

    @Override
    public void deleteById(Long id) {
        rentRepository.deleteById(id);
    }

    public void initRentFromDto(Rent rent, RentDto rentDto, Client client, Car car) {
        rent.setDateStartRent(rentDto.getDateStartRent());
        rent.setDateFinishRent(rentDto.getDateFinishRent());
        rent.setId(rentDto.getId());
        rent.setCar(car);
        rent.setClient(client);
    }
}
