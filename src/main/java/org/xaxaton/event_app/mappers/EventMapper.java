package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.models.Event;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper{
    protected final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EventDTO toDTO(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    public Event toModel(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }

    public List<EventDTO> toListOfDTOs(List<Event> events) {
        List<EventDTO> result = new ArrayList<>();

        for (Event event : events) {
            result.add(toDTO(event));
        }

        return result;
    }

    public List<Event> toListOfModels(List<EventDTO> eventDTOs) {
        List<Event> result = new ArrayList<>();

        for (EventDTO eventDTO : eventDTOs) {
            result.add(toModel(eventDTO));
        }

        return result;
    }
}
