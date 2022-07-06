package com.example.paragonmain.Services;

import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Requests.CarRequest;
import com.example.paragonmain.Requests.ModelRequest;

import java.util.List;

public interface CarService {
    Car getCarById(Long id);
    List<Car> getAllCars();
    List<Car> getAllCarsByBrand(Long brand_id);
    void addCar(CarRequest carRequest);
    List<Brand> getAllBrands();
    List<Model> getAllModelsByBrand(Long brand_id);
    void addBrand(Brand brand);
    void addModel(ModelRequest modelRequest);
}
