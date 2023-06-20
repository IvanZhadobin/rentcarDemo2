package com.example.demo.dto;

import com.example.demo.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private LocalDate yearOfRelease;
    private Integer price;
    private String mark;
    private String model;

    public CarDto(Car c) {
        this.id = c.getId();
        this.price = c.getPrice();
        this.mark = c.getMark();
        this.model = c.getModel();
        this.yearOfRelease = c.getYearOfRelease();
    }

}