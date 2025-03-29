package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.ShoppingItemDTO;
import org.xaxaton.event_app.models.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingItemMapper {

    private final ModelMapper modelMapper;

    public ShoppingItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ShoppingItemDTO toDTO(ShoppingItem shoppingItem) {
        return modelMapper.map(shoppingItem, ShoppingItemDTO.class);
    }

    public ShoppingItem toModel(ShoppingItemDTO shoppingItemDTO) {
        return modelMapper.map(shoppingItemDTO, ShoppingItem.class);
    }

    public List<ShoppingItemDTO> toListOfDTOs(List<ShoppingItem> shoppingItems) {
        List<ShoppingItemDTO> result = new ArrayList<>();

        for (ShoppingItem shoppingItem : shoppingItems) {
            result.add(toDTO(shoppingItem));
        }

        return result;
    }

    public List<ShoppingItem> toListOfModels(List<ShoppingItemDTO> shoppingItemDTOs) {
        List<ShoppingItem> result = new ArrayList<>();

        for (ShoppingItemDTO shoppingItemDTO : shoppingItemDTOs) {
            result.add(toModel(shoppingItemDTO));
        }

        return result;
    }
}
