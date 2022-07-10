package com.example.paragonmain.Objects;

import com.example.paragonmain.Enums.Condition;
import lombok.Value;

@Value
public class Car {
    Long id;
    int year;
    Double price;
    Brand brand;
    Model model;
    boolean sold;
    Condition condition;
}
