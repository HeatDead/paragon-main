package com.example.paragonmain.Controllers;

import com.example.paragonmain.Exceptions.ObjectNotFoundException;
import com.example.paragonmain.Mappers.BrandToDtoMapper;
import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Outputs.BrandOutput;
import com.example.paragonmain.Outputs.CarOutput;
import com.example.paragonmain.Outputs.ModelOutput;
import com.example.paragonmain.Requests.*;
import com.example.paragonmain.Services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    private final BrandToDtoMapper brandMapper;

    @GetMapping("/getCarById")
    public Car getCarById(@RequestParam Long id) throws ObjectNotFoundException {
        return carService.getCarById(id);
    }

    @GetMapping
    public List<Car> getAllCars(@RequestParam(required = false) Long brand_id) throws ObjectNotFoundException {
        if (brand_id != null)
            return carService.getAllCarsByBrand(brand_id);

        return carService.getAllCars();
    }

    @GetMapping("/allIds")
    public List<Long> getAllCarsId(){
        return carService.getAllCarsIds();
    }

    @GetMapping("/allInfo")
    public List<Car> getAllCarsInfo(@RequestParam(required = false) Long brand_id) throws ObjectNotFoundException {
        if (brand_id != null)
            return carService.getAllCarsByBrand(brand_id);

        return carService.getAllCars();
    }

    //Запрос микросервиса
    @GetMapping("/carsOf")
    public List<Car> getAllCarsOfUser(@RequestParam String owner){
        return carService.getAllCarsByOwner(owner);
    }

    public CarOutput carToCarOutput(Car car){
        CarOutput carOutput = new CarOutput();
        carOutput.setId(car.getId());
        carOutput.setPrice(car.getPrice());
        carOutput.setYear(car.getYear());

        carOutput.setBrand(new BrandOutput(car.getBrand().getId(), car.getBrand().getBrand()));
        carOutput.setModel(new ModelOutput(car.getModel().getId(), car.getModel().getModel()));

        carOutput.setSold(car.isSold());
        carOutput.setCondition(car.getCondition());
        return carOutput;
    }

    @PostMapping
    public void addCar(@RequestBody CarRequest request) throws ObjectNotFoundException {
        carService.addCar(request);
    }

    @PostMapping("/edit")
    public void editCar(@RequestBody EditCarRequest request) throws ObjectNotFoundException{
        carService.editCar(request);
    }

    @PostMapping("/delete")
    public void deleteCar(@RequestParam Long id) throws ObjectNotFoundException{
        carService.deleteCar(id);
    }

    @GetMapping("/brand")
    public List<Brand> getAllBrands(){
        return carService.getAllBrands();
    }

    @GetMapping("/model")
    public List<Model> getAllModels(@RequestParam Long brand_id) throws ObjectNotFoundException{
        return carService.getAllModelsByBrand(brand_id);
    }

    @PostMapping("/brand")
    public void addBrand(@RequestBody BrandRequest request){
        carService.addBrand(brandMapper.AddBrandRequestToBrand(request));
    }

    @PostMapping("/model")
    public void addModel(@RequestBody ModelRequest request) throws ObjectNotFoundException{
        carService.addModel(request);
    }

    //Запрос микросервиса
    @PostMapping("/sold")
    public void soldCar(@RequestBody SoldRequest soldRequest) throws ObjectNotFoundException{
        carService.soldCar(soldRequest);
    }
}
