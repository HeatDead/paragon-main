package com.example.paragonmain.Repositories;

import com.example.paragonmain.Entities.BrandEntity;
import com.example.paragonmain.Entities.CarEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<CarEntity, Long> {
    List<CarEntity> findAllByBrand(BrandEntity brand);
    List<CarEntity> findAllByOwner(String owner);
}
