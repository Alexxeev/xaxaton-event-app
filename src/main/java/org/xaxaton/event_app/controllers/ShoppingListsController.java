package org.xaxaton.event_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.ShoppingListDTO;
import org.xaxaton.event_app.mappers.ShoppingListMapper;
import org.xaxaton.event_app.models.ShoppingList;
import org.xaxaton.event_app.repo.ShoppingListRepo;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListsController extends BaseController<
        ShoppingList, ShoppingListDTO, ShoppingListRepo, ShoppingListMapper> {
    public ShoppingListsController(ShoppingListRepo repo, ShoppingListMapper mapper) {
        super(repo, mapper);
    }
}
