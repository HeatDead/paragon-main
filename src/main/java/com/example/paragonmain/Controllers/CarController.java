package com.example.paragonmain.Controllers;

import com.example.paragonmain.Mappers.BrandToDtoMapper;
import com.example.paragonmain.Mappers.CarToDtoMapper;
import com.example.paragonmain.Mappers.ModelToDtoMapper;
import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Outputs.BrandOutput;
import com.example.paragonmain.Outputs.CarOutput;
import com.example.paragonmain.Outputs.ModelOutput;
import com.example.paragonmain.Requests.*;
import com.example.paragonmain.Services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarToDtoMapper carMapper;

    private final BrandToDtoMapper brandMapper;
    private final ModelToDtoMapper modelMapper;

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping
    public List<Car> getAllCars(@RequestParam(required = false) Long brand_id) {
        if (brand_id != null)
            return carService.getAllCarsByBrand(brand_id);

        return carService.getAllCars();
    }

    @GetMapping("/allIds")
    public List<Long> getAllCarsId(){
        List<Long> ids = new ArrayList<>();
        List<CarOutput> carOutputs = carListToCarOutputList(carService.getAllCars());

        for(CarOutput carOutput : carOutputs){
            ids.add(carOutput.getId());
        }

        return ids;
    }

    @GetMapping("/allInfo")
    public List<Car> getAllCarsInfo(@RequestParam(required = false) Long brand_id) {
        if (brand_id != null)
            return carService.getAllCarsByBrand(brand_id);

        return carService.getAllCars();
    }

    public List<CarOutput> carListToCarOutputList(List<Car> cars) {
        List<CarOutput> carOutputs = new ArrayList<>();
        for (Car car: cars)
            carOutputs.add(carToCarOutput(car));
        return carOutputs;
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
    public void addCar(@RequestBody CarRequest request) {
        carService.addCar(request);
    }

    @PostMapping("/edit")
    public void editCar(@RequestBody EditCarRequest request){
        carService.editCar(request);
    }

    @PostMapping("/delete/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }

    @GetMapping("/brand")
    public List<Brand> getAllBrands(){
        return carService.getAllBrands();
    }

    @GetMapping("/model")
    public List<Model> getAllModels(@RequestParam Long brand_id){
        return carService.getAllModelsByBrand(brand_id);
    }

    @PostMapping("/brand")
    public void addBrand(@RequestBody BrandRequest request){
        carService.addBrand(brandMapper.AddBrandRequestToBrand(request));
    }

    @PostMapping("/model")
    public void addModel(@RequestBody ModelRequest request){
        carService.addModel(request);
    }

    @PostMapping("/sold")
    public void soldCar(@RequestBody SoldRequest soldRequest){
        carService.soldCar(soldRequest);
    }
}
