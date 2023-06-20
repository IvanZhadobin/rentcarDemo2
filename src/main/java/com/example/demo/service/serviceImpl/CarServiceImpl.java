package com.example.demo.service.serviceImpl;

import com.example.demo.dto.CarDto;
import com.example.demo.entities.Car;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    CarRepository carRepository;

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Машина с данным id: " + id + " не найдена!"));
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car add(CarDto carDto) {
        if (carRepository.existsById(carDto.getId())) {
            throw new ResourceAlreadyExistsException("Машина с таким id уже существует");
        }
        Car car = new Car();
        car.setId(carDto.getId());

        initCarFromDto(car, carDto);
        carRepository.save(car);
        return car;
    }

    @Override
    public Car update(CarDto carDto) {
        Car car = carRepository.findById(carDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Машина с id = " + carDto.getId() + " не существует"));
        initCarFromDto(car, carDto);
        carRepository.save(car);
        return car;
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    private void initCarFromDto(Car car, CarDto carDto) {
        car.setId(carDto.getId());
        car.setMark(carDto.getMark());
        car.setModel(carDto.getModel());
        car.setPrice(carDto.getPrice());
        car.setYearOfRelease(carDto.getYearOfRelease());
    }
}
