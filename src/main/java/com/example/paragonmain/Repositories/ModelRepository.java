package com.example.paragonmain.Repositories;

import com.example.paragonmain.Entities.BrandEntity;
import com.example.paragonmain.Entities.ModelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelRepository extends CrudRepository<ModelEntity, Long> {
    List<ModelEntity> findAllByBrand(BrandEntity brand);
}
