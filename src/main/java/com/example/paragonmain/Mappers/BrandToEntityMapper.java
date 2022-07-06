package com.example.paragonmain.Mappers;

import com.example.paragonmain.Entities.BrandEntity;
import com.example.paragonmain.Objects.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandToEntityMapper {
    BrandEntity brandToBrandEntity(Brand brand);
    Brand brandEntityToBrand(BrandEntity brandEntity);
}
