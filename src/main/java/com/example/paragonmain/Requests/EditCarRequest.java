package com.example.paragonmain.Requests;

import com.example.paragonmain.Enums.Condition;
import lombok.Data;

@Data
public class EditCarRequest {
    Long id;
    int year;
    Double price;
    Long brand_id;
    Long model_id;
    Condition condition;
}
