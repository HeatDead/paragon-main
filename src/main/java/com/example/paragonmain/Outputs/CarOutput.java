package com.example.paragonmain.Outputs;

import com.example.paragonmain.Enums.Condition;
import lombok.Data;
import lombok.Value;

@Data
public class CarOutput {
    Long id;
    int year;
    Double price;
    BrandOutput brand;
    ModelOutput model;
    String img_url;
    boolean sold;
    Condition condition;
}
