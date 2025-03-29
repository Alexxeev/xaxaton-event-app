package org.xaxaton.event_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.xaxaton.event_app.mappers.BaseMapper;
import org.xaxaton.event_app.repo.BaseRepo;

import java.util.List;


public abstract class BaseController<M, D, R extends BaseRepo<M>, P extends BaseMapper<M, D>> {

    protected final R repo;
    protected final P mapper;

    public BaseController(R repo, P mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @GetMapping
    public List<D> getAll(Class<D> dtoClass) {
        return mapper.toListOfDTOs(repo.findAll(), dtoClass);
    }
}
