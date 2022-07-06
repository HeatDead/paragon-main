package com.example.paragonmain.Mappers;

import com.example.paragonmain.Objects.Brand;
import com.example.paragonmain.Requests.BrandRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandToDtoMapper {
    Brand AddBrandRequestToBrand(BrandRequest brandRequest);
}
