package com.example.demo.entities;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Car {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "years_of_release")
    private LocalDate yearOfRelease;
    @Column(name = "price")
    private Integer price;
    @Column(name = "mark")
    private String mark;
    @Column(name = "model")
    private String model;
}
