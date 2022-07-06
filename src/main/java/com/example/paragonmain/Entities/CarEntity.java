package com.example.paragonmain.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int year;
    private Double price;

    @ManyToOne
    private BrandEntity brand;

    @OneToOne
    private ModelEntity model;
}
