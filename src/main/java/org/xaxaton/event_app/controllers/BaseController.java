package org.xaxaton.event_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.xaxaton.event_app.mappers.BaseMapper;
import org.xaxaton.event_app.repo.BaseRepo;

import java.util.List;


public abstract class BaseController<M, D> {

    protected final BaseRepo<M> repo;
    protected final BaseMapper<M, D> mapper;


    public BaseController(BaseRepo<M> repo, BaseMapper<M, D> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<D> getAll(Class<D> dtoClass) {
        return mapper.toListOfDTOs(repo.findAll(), dtoClass);
    }
}
