package com.example.paragonmain.Services;

import com.example.paragonmain.Entities.BrandEntity;
import com.example.paragonmain.Entities.CarEntity;
import com.example.paragonmain.Entities.ModelEntity;
import com.example.paragonmain.Mappers.BrandToEntityMapper;
import com.example.paragonmain.Mappers.CarToEntityMapper;
import com.example.paragonmain.Mappers.ModelToEntityMapper;
import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Repositories.BrandRepository;
import com.example.paragonmain.Repositories.CarRepository;
import com.example.paragonmain.Repositories.ModelRepository;
import com.example.paragonmain.Requests.CarRequest;
import com.example.paragonmain.Requests.EditCarRequest;
import com.example.paragonmain.Requests.ModelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCarService implements CarService {
    private final CarRepository carRepository;
    private final CarToEntityMapper carMapper;

    private final BrandRepository brandRepository;
    private final BrandToEntityMapper brandMapper;

    private final ModelRepository modelRepository;
    private final ModelToEntityMapper modelMapper;

    @Override
    public Car getCarById(Long id) {
        CarEntity carEntity = carRepository.findById(id).get();
        return carMapper.carEntityToCar(carEntity);
    }

    @Override
    public List<Car> getAllCars() {
        Iterable<CarEntity> iterable = carRepository.findAll();

        ArrayList<Car> cars = new ArrayList<>();
        for (CarEntity carEntity : iterable){
            if(carEntity.getBrand() == null || carEntity.getModel() == null){
                carRepository.delete(carEntity);
                continue;
            }
            cars.add(carMapper.carEntityToCar(carEntity));
        }

        return cars;
    }

    @Override
    public List<Car> getAllCarsByBrand(Long brand_id) {
        BrandEntity brandEntity = brandRepository.findById(brand_id).get();
        if(brandEntity == null)
            return null;

        Iterable<CarEntity> iterable = carRepository.findAllByBrand(brandEntity);
        ArrayList<Car> cars = new ArrayList<>();
        for (CarEntity carEntity : iterable)
            cars.add(carMapper.carEntityToCar(carEntity));

        return cars;
    }

    @Override
    public void addCar(CarRequest carRequest) {
        BrandEntity brandEntity = brandRepository.findById(carRequest.getBrand_id()).get();
        ModelEntity modelEntity = modelRepository.findById(carRequest.getModel_id()).get();
        if(brandEntity == null || modelEntity == null)
            return;

        if(modelEntity.getBrand().getId() != brandEntity.getId())
            return;

        CarEntity carEntity = new CarEntity();
        carEntity.setPrice(carRequest.getPrice());
        carEntity.setYear(carRequest.getYear());
        carEntity.setBrand(brandEntity);
        carEntity.setModel(modelEntity);
        carEntity.setSold(false);
        carEntity.setCondition(carRequest.getCondition());

        carRepository.save(carEntity);
    }

    @Override
    public void editCar(EditCarRequest editCarRequest) {
        if(carRepository.findById(editCarRequest.getId()).get() != null){
            BrandEntity brandEntity = brandRepository.findById(editCarRequest.getBrand_id()).get();
            ModelEntity modelEntity = modelRepository.findById(editCarRequest.getModel_id()).get();
            if(brandEntity == null || modelEntity == null)
                return;

            if(modelEntity.getBrand().getId() != brandEntity.getId())
                return;

            CarEntity carEntity = new CarEntity();
            carEntity.setId(editCarRequest.getId());
            carEntity.setPrice(editCarRequest.getPrice());
            carEntity.setYear(editCarRequest.getYear());
            carEntity.setBrand(brandEntity);
            carEntity.setModel(modelEntity);
            carEntity.setCondition(editCarRequest.getCondition());

            carRepository.save(carEntity);
        }
    }

    @Override
    public void deleteCar(Long car_id) {
        CarEntity carEntity = carRepository.findById(car_id).get();
        if(carEntity != null)
            carRepository.delete(carEntity);
    }

    @Override
    public List<Brand> getAllBrands() {
        Iterable<BrandEntity> iterable = brandRepository.findAll();

        ArrayList<Brand> brands = new ArrayList<>();
        for (BrandEntity brandEntity : iterable) {
            if(brandEntity.getBrand() == null)
            {
                brandRepository.delete(brandEntity);
                continue;
            }
            brands.add(brandMapper.brandEntityToBrand(brandEntity));
        }

        return brands;
    }

    @Override
    public List<Model> getAllModelsByBrand(Long brand_id) {
        BrandEntity brandEntity = brandRepository.findById(brand_id).get();
        if(brandEntity == null)
            return null;

        Iterable<ModelEntity> iterable = modelRepository.findAllByBrand(brandEntity);
        ArrayList<Model> models = new ArrayList<>();
        for (ModelEntity modelEntity : iterable)
            models.add(modelMapper.modelEntityToModel(modelEntity));

        return models;
    }

    @Override
    public void addBrand(Brand brand) {
        BrandEntity brandEntity = brandMapper.brandToBrandEntity(brand);
        brandRepository.save(brandEntity);
    }

    @Override
    public void addModel(ModelRequest modelRequest) {
        BrandEntity brandEntity = brandRepository.findById(modelRequest.getBrand_id()).get();
        if(brandEntity == null)
            return;
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setModel(modelRequest.getModel());
        modelEntity.setBrand(brandEntity);
        modelRepository.save(modelEntity);
    }
}
