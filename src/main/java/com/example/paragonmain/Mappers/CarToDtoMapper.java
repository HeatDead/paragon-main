package com.example.paragonmain.Mappers;

import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Requests.CarRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarToDtoMapper {
    Car AddCarRequestToCar(CarRequest carRequest);
}
