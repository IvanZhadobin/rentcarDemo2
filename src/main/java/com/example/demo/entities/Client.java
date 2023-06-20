package com.example.demo.entities;



import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Client {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "passport")
    private String passport;
    @Column(name = "driver_license")
    private String driverLicense;

}
