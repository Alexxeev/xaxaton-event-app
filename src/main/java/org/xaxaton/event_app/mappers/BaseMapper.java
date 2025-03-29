package org.xaxaton.event_app.mappers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BaseMapper<M, D> {

    private final ModelMapper modelMapper;

    @Autowired
    public BaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public D toDTO(M model, Class<D> dtoClass) {
        return modelMapper.map(model, dtoClass);
    }

    public M toModel(D dto, Class<M> modelClass) {
        return modelMapper.map(dto, modelClass);
    }

    public List<D> toListOfDTOs(List<M> entities, Class<D> dtoClass) {
        List<D> result = new ArrayList<>();
        for (M model : entities) {
            result.add(toDTO(model, dtoClass));
        }
        return result;
    }

    public List<M> toListOfModels(List<D> dtos, Class<M> modelClass) {
        List<M> result = new ArrayList<>();
        for (D dto : dtos) {
            result.add(toModel(dto, modelClass));
        }
        return result;
    }

}
