package com.example.paragonmain.Objects;

import lombok.Value;

@Value
public class Car {
    Long id;
    int year;
    Double price;
    Brand brand;
    Model model;
}
