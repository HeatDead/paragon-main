package com.example.paragonmain.Requests;

import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Model;
import lombok.Data;

@Data
public class CarRequest {
    int year;
    Double price;
    Long brand_id;
    Long model_id;
}
