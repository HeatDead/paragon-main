package com.example.paragonmain.Controllers;

import com.example.paragonmain.Mappers.BrandToDtoMapper;
import com.example.paragonmain.Mappers.CarToDtoMapper;
import com.example.paragonmain.Mappers.ModelToDtoMapper;
import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Requests.BrandRequest;
import com.example.paragonmain.Requests.CarRequest;
import com.example.paragonmain.Requests.ModelRequest;
import com.example.paragonmain.Services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
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

    @PostMapping
    public void addCar(@RequestBody CarRequest request) {
        carService.addCar(request);
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
}
