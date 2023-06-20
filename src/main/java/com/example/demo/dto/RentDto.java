package com.example.demo.dto;

import com.example.demo.entities.Car;
import com.example.demo.entities.Client;
import com.example.demo.entities.Rent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private Long id;
    private LocalDate dateStartRent;
    private LocalDate dateFinishRent;
    private Long clientId;
    private Long carId;

    public RentDto(Rent r) {
        this.id = r.getId();
        this.dateStartRent = r.getDateStartRent();
        this.dateFinishRent = r.getDateFinishRent();
        this.clientId = r.getClient().getId();
        this.carId = r.getCar().getId();
    }
}
