package org.xaxaton.event_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.mappers.EventMapper;
import org.xaxaton.event_app.models.Event;
import org.xaxaton.event_app.models.Member;
import org.xaxaton.event_app.repo.EventRepo;
import org.xaxaton.event_app.repo.MemberRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("events/{id}/events")
public class EventsController extends BaseController<Event, EventDTO, EventRepo, EventMapper> {
    public EventsController(EventRepo repo, EventMapper mapper, MemberRepo memberRepo) {
        super(repo, mapper);
        this.memberRepo = memberRepo;
    }

    private final MemberRepo memberRepo;

    @PostMapping
    public ResponseEntity<Event> createNew(@PathVariable("id") int id,
                                           @RequestBody EventDTO eventDTO) {
        Optional<Member> admin = memberRepo.findById(id);
        if (admin.isEmpty())
            return ResponseEntity.badRequest().build();
        Event event = mapper.toModel(eventDTO);
        if (repo.existsByName(event.getName()))
            return ResponseEntity.badRequest().build();

        event.setAdmin(admin.get());
        Event saved = repo.save(event);
        return ResponseEntity.ok(saved);
    }




}
