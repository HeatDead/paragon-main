package com.example.paragonmain.Services;

import com.example.paragonmain.Exceptions.ObjectNotFoundException;
import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Requests.CarRequest;
import com.example.paragonmain.Requests.EditCarRequest;
import com.example.paragonmain.Requests.ModelRequest;
import com.example.paragonmain.Requests.SoldRequest;

import java.util.List;

public interface CarService {
    Car getCarById(Long id) throws ObjectNotFoundException;
    List<Car> getAllCars();
    List<Car> getAllCarsByBrand(Long brand_id) throws ObjectNotFoundException ;
    List<Car> getAllCarsByOwner(String user);
    List<Long> getAllCarsIds();
    void addCar(CarRequest carRequest) throws ObjectNotFoundException;
    void editCar(EditCarRequest editCarRequest) throws ObjectNotFoundException;
    void deleteCar(Long car_id) throws ObjectNotFoundException;
    List<Brand> getAllBrands();
    List<Model> getAllModelsByBrand(Long brand_id) throws ObjectNotFoundException;
    void addBrand(Brand brand);
    void addModel(ModelRequest modelRequest) throws ObjectNotFoundException;
    void soldCar(SoldRequest soldRequest) throws ObjectNotFoundException;
}
