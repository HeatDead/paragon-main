package com.example.paragonmain.Entities;

import com.example.paragonmain.Enums.Condition;
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

    private boolean sold;

    private String owner;

    @ManyToOne
    private BrandEntity brand;

    @OneToOne
    private ModelEntity model;

    private Condition condition;
}
