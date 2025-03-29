package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.ShoppingItemDTO;
import org.xaxaton.event_app.models.ShoppingItem;

@Component
public class ShoppingItemMapper extends BaseMapper<ShoppingItem, ShoppingItemDTO> {
    public ShoppingItemMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public ShoppingItemDTO toDTO(ShoppingItem shoppingItem) {
        return modelMapper.map(shoppingItem, ShoppingItemDTO.class);
    }

    @Override
    public ShoppingItem toModel(ShoppingItemDTO shoppingItemDTO) {
        return modelMapper.map(shoppingItemDTO, ShoppingItem.class);
    }
}
