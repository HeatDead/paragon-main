package com.example.paragonmain.Services;

import com.example.paragonmain.Entities.BrandEntity;
import com.example.paragonmain.Entities.CarEntity;
import com.example.paragonmain.Entities.ModelEntity;
import com.example.paragonmain.Exceptions.ObjectNotFoundException;
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
import com.example.paragonmain.Requests.SoldRequest;
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
    public Car getCarById(Long id) throws ObjectNotFoundException {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Автомобиль с этим id не найден"));
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
            if(carEntity.isSold())
                continue;
            cars.add(carMapper.carEntityToCar(carEntity));
        }

        return cars;
    }

    @Override
    public List<Car> getAllCarsByBrand(Long brand_id) throws ObjectNotFoundException {
        BrandEntity brandEntity = brandRepository.findById(brand_id)
                .orElseThrow(()-> new ObjectNotFoundException("Бренд с этим id не найден"));

        Iterable<CarEntity> iterable = carRepository.findAllByBrand(brandEntity);
        ArrayList<Car> cars = new ArrayList<>();
        for (CarEntity carEntity : iterable) {
            if(carEntity.isSold())
                continue;
            cars.add(carMapper.carEntityToCar(carEntity));
        }

        return cars;
    }

    @Override
    public List<Car> getAllCarsByOwner(String user) {
        Iterable<CarEntity> iterable = carRepository.findAllByOwner(user);

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
    public List<Long> getAllCarsIds() {
        Iterable<CarEntity> iterable = carRepository.findAll();

        List<Long> cars = new ArrayList<>();
        for (CarEntity carEntity : iterable){
            if(carEntity.getBrand() == null || carEntity.getModel() == null){
                carRepository.delete(carEntity);
                continue;
            }
            cars.add(carEntity.getId());
        }

        return cars;
    }

    @Override
    public void addCar(CarRequest carRequest) throws ObjectNotFoundException {
        BrandEntity brandEntity = brandRepository.findById(carRequest.getBrand_id())
                .orElseThrow(()-> new ObjectNotFoundException("Бренд с этим id не найден"));

        ModelEntity modelEntity = modelRepository.findById(carRequest.getModel_id())
                .orElseThrow(()-> new ObjectNotFoundException("Модель с этим id не найдена"));

        if(brandEntity == null || modelEntity == null)
            throw new IllegalArgumentException("Не присвоен бренд или модель");

        if(modelEntity.getBrand().getId() != brandEntity.getId())
            throw new IllegalArgumentException("Модель не соответствует бренду");

        CarEntity carEntity = new CarEntity();
        carEntity.setPrice(carRequest.getPrice());
        carEntity.setYear(carRequest.getYear());
        carEntity.setBrand(brandEntity);
        carEntity.setModel(modelEntity);
        carEntity.setSold(false);
        carEntity.setImg_url(carRequest.getImg_url());
        carEntity.setCondition(carRequest.getCondition());

        carRepository.save(carEntity);
    }

    @Override
    public void editCar(EditCarRequest editCarRequest) throws ObjectNotFoundException {
        carRepository.findById(editCarRequest.getId())
                .orElseThrow(()-> new ObjectNotFoundException("Автомобиль с этим id не найден"));

            BrandEntity brandEntity = brandRepository.findById(editCarRequest.getBrand_id())
                    .orElseThrow(()-> new ObjectNotFoundException("Бренд с этим id не найден"));

            ModelEntity modelEntity = modelRepository.findById(editCarRequest.getModel_id())
                    .orElseThrow(()-> new ObjectNotFoundException("Модель с этим id не найдена"));

            if(brandEntity == null || modelEntity == null)
                throw new IllegalArgumentException("Не присвоен бренд или модель");

            if(modelEntity.getBrand().getId() != brandEntity.getId())
                throw new IllegalArgumentException("Модель не соответствует бренду");

            CarEntity carEntity = new CarEntity();
            carEntity.setId(editCarRequest.getId());
            carEntity.setPrice(editCarRequest.getPrice());
            carEntity.setYear(editCarRequest.getYear());
            carEntity.setBrand(brandEntity);
            carEntity.setModel(modelEntity);
            carEntity.setImg_url(editCarRequest.getImg_url());
            carEntity.setCondition(editCarRequest.getCondition());

            carRepository.save(carEntity);

    }

    @Override
    public void deleteCar(Long car_id) throws ObjectNotFoundException {
        CarEntity carEntity = carRepository.findById(car_id)
                .orElseThrow(()-> new ObjectNotFoundException("Бренд с этим id не найден"));

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
    public List<Model> getAllModelsByBrand(Long brand_id) throws ObjectNotFoundException {
        BrandEntity brandEntity = brandRepository.findById(brand_id)
                .orElseThrow(()-> new ObjectNotFoundException("Бренд с этим id не найден"));

        Iterable<ModelEntity> iterable = modelRepository.findAllByBrand(brandEntity);
        ArrayList<Model> models = new ArrayList<>();
        for (ModelEntity modelEntity : iterable)
            models.add(modelMapper.modelEntityToModel(modelEntity));

        return models;
    }

    @Override
    public void addBrand(Brand brand) {
        if(brand.getBrand().equals(""))
            throw new IllegalArgumentException("Название бренда не может быть пустым");
        BrandEntity brandEntity = brandMapper.brandToBrandEntity(brand);
        brandRepository.save(brandEntity);
    }

    @Override
    public void addModel(ModelRequest modelRequest) throws ObjectNotFoundException {
        if(modelRequest.getModel().equals(""))
            throw new IllegalArgumentException("Название модели не может быть пустым");

        BrandEntity brandEntity = brandRepository.findById(modelRequest.getBrand_id())
                .orElseThrow(()-> new ObjectNotFoundException("Бренд с этим id не найден"));

        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setModel(modelRequest.getModel());
        modelEntity.setBrand(brandEntity);
        modelRepository.save(modelEntity);
    }

    @Override
    public void soldCar(SoldRequest soldRequest) throws ObjectNotFoundException {
        CarEntity carEntity = carRepository.findById(soldRequest.getCar_id())
                .orElseThrow(()-> new ObjectNotFoundException("Автомобиль с этим id не найден"));

        carEntity.setOwner(soldRequest.getUsername());
        carEntity.setSold(true);
        carRepository.save(carEntity);
    }
}
