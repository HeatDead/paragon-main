package com.example.paragonmain.Mappers;

import com.example.paragonmain.Entities.CarEntity;
import com.example.paragonmain.Objects.Car;
import com.example.paragonmain.Requests.EditCarRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarToEntityMapper {
    CarEntity carToCarEntity(Car car);
    Car carEntityToCar(CarEntity carEntity);
    CarEntity editCarRequestToCarEntity(EditCarRequest editCarRequest);
}
