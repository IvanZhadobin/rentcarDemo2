package com.example.demo.dto;

import com.example.demo.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private LocalDate birthday;
    private String passport;
    private String driverLicense;

    public ClientDto(Client c) {
        this.id = c.getId();
        this.name = c.getName();
        this.username = c.getUsername();
        this.password = c.getPassword();
        this.birthday = c.getBirthday();
        this.passport = c.getPassport();
        this.driverLicense = c.getDriverLicense();
    }
}
