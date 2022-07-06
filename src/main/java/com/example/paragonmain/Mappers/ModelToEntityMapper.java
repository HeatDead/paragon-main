package com.example.paragonmain.Mappers;

import com.example.paragonmain.Entities.ModelEntity;
import com.example.paragonmain.Objects.Model;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelToEntityMapper {
    ModelEntity modelToModelEntity(Model model);
    Model modelEntityToModel(ModelEntity modelEntity);
}
