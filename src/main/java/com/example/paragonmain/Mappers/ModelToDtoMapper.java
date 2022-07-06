package com.example.paragonmain.Mappers;

import com.example.paragonmain.Objects.Model;
import com.example.paragonmain.Requests.ModelRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelToDtoMapper {
    Model AddModelRequestToModel(ModelRequest modelRequest);
}
