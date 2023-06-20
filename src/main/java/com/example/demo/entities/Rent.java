package com.example.demo.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class Rent {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "date_start_rent")
    private LocalDate dateStartRent;
    @Column(name = "date_finish_rent")
    private LocalDate dateFinishRent;

    @ManyToOne
    private Car car;
    @ManyToOne
    private Client client;


}
