package org.xaxaton.event_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.mappers.EventMapper;
import org.xaxaton.event_app.models.Event;
import org.xaxaton.event_app.repo.EventRepo;

@RestController
@RequestMapping("/events")
public class EventsController extends BaseController<Event, EventDTO, EventRepo, EventMapper> {
    public EventsController(EventRepo repo, EventMapper mapper) {
        super(repo, mapper);
    }
}
