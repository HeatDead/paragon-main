package com.example.paragonmain.Controllers;

import com.example.paragonmain.Exceptions.ObjectNotFoundException;
import com.example.paragonmain.Mappers.BrandToDtoMapper;
import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Requests.*;
import com.example.paragonmain.Services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping("/cars")
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    private final BrandToDtoMapper brandMapper;

    @RequestMapping(value = "/getCarById", method = RequestMethod.GET)
    public Car getCarById(@RequestParam Long id) throws ObjectNotFoundException {
        return carService.getCarById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getAllCars(@RequestParam(required = false) Long brand_id) throws ObjectNotFoundException {
        if (brand_id != null)
            return carService.getAllCarsByBrand(brand_id);

        return carService.getAllCars();
    }

    @RequestMapping(value = "/allIds", method = RequestMethod.GET)
    public List<Long> getAllCarsId(){
        return carService.getAllCarsIds();
    }

    @RequestMapping(value = "/allInfo", method = RequestMethod.GET)
    public List<Car> getAllCarsInfo(@RequestParam(required = false) Long brand_id) throws ObjectNotFoundException {
        if (brand_id != null)
            return carService.getAllCarsByBrand(brand_id);

        return carService.getAllCars();
    }

    //Запрос микросервиса
    @RequestMapping(value = "/carsOf", method = RequestMethod.GET)
    public List<Car> getAllCarsOfUser(@RequestParam String owner){
        return carService.getAllCarsByOwner(owner);
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public void addCar(@RequestBody CarRequest request) throws ObjectNotFoundException {
        carService.addCar(request);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public void editCar(@RequestBody EditCarRequest request) throws ObjectNotFoundException{
        carService.editCar(request);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteCar(@RequestParam Long id) throws ObjectNotFoundException{
        carService.deleteCar(id);
    }

    @RequestMapping(value = "/brand", method = RequestMethod.GET)
    public List<Brand> getAllBrands(){
        return carService.getAllBrands();
    }

    @RequestMapping(value = "/model", method = RequestMethod.GET)
    public List<Model> getAllModels(@RequestParam Long brand_id) throws ObjectNotFoundException{
        return carService.getAllModelsByBrand(brand_id);
    }

    @RequestMapping(value = "/addBrand", method = RequestMethod.POST)
    public void addBrand(@RequestBody BrandRequest request){
        carService.addBrand(brandMapper.AddBrandRequestToBrand(request));
    }

    @RequestMapping(value = "/addModel", method = RequestMethod.POST)
    public void addModel(@RequestBody ModelRequest request) throws ObjectNotFoundException{
        carService.addModel(request);
    }

    //Запрос микросервиса
    @RequestMapping(value = "/sold", method = RequestMethod.POST)
    public void soldCar(@RequestBody SoldRequest soldRequest) throws ObjectNotFoundException{
        carService.soldCar(soldRequest);
    }
}
