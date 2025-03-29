package org.xaxaton.event_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.ShoppingItemDTO;
import org.xaxaton.event_app.mappers.ShoppingItemMapper;

import org.xaxaton.event_app.models.ShoppingItem;
import org.xaxaton.event_app.repo.ShoppingItemRepo;


@RestController
@RequestMapping("/shopping-items")
public class ShoppingItemsController extends BaseController<ShoppingItem,
        ShoppingItemDTO,
        ShoppingItemRepo,
        ShoppingItemMapper> {
    public ShoppingItemsController(ShoppingItemRepo repo, ShoppingItemMapper mapper) {
        super(repo, mapper);
    }


    
}

