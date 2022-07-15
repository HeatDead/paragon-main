package com.example.paragonmain.Services;

import com.example.paragonmain.Entities.CarEntity;
import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Requests.CarRequest;
import com.example.paragonmain.Requests.EditCarRequest;
import com.example.paragonmain.Requests.ModelRequest;
import com.example.paragonmain.Requests.SoldRequest;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface CarService {
    Car getCarById(Long id);
    List<Car> getAllCars();
    List<Car> getAllCarsByBrand(Long brand_id);
    List<Car> getAllCarsByOwner(String user);
    List<Long> getAllCarsIds();
    void addCar(CarRequest carRequest);
    void editCar(EditCarRequest editCarRequest);
    void deleteCar(Long car_id);
    List<Brand> getAllBrands();
    List<Model> getAllModelsByBrand(Long brand_id);
    void addBrand(Brand brand);
    void addModel(ModelRequest modelRequest);
    void soldCar(SoldRequest soldRequest);
}
