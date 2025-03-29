package org.xaxaton.event_app.mappers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<M, D> {

    protected final ModelMapper modelMapper;

    @Autowired
    public BaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public abstract D toDTO(M model);

    public abstract M toModel(D dto);

    public List<D> toListOfDTOs(List<M> entities) {
        List<D> result = new ArrayList<>();
        for (M model : entities) {
            result.add(toDTO(model));
        }
        return result;
    }

    public List<M> toListOfModels(List<D> dtos) {
        List<M> result = new ArrayList<>();
        for (D dto : dtos) {
            result.add(toModel(dto));
        }
        return result;
    }

}
