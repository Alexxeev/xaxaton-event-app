package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.models.Event;

@Component
public class EventMapper extends BaseMapper<Event, EventDTO> {
    public EventMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public EventDTO toDTO(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    public Event toModel(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }
    
}
