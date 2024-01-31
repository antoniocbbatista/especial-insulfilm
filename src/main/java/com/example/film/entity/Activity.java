package com.example.film.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String service;

    private Double price;

    @Column(name = "data_cadastro")
    private LocalDate dateService;

    @Column(name = "hora_cadastro")
    private LocalTime timeService;

    @ManyToOne
    @JoinColumn(name = "partner-id")
    private Partner partner;

    public void setDateService(LocalDate dateService){
        this.dateService = dateService;
    }
}
