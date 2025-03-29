package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.ShoppingListDTO;
import org.xaxaton.event_app.models.ShoppingList;

@Component
public class ShoppingListMapper extends BaseMapper<ShoppingList, ShoppingListDTO> {
    public ShoppingListMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
