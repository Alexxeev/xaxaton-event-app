package org.xaxaton.event_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.dto.MemberDTO;
import org.xaxaton.event_app.mappers.EventMapper;
import org.xaxaton.event_app.models.Event;
import org.xaxaton.event_app.models.Member;
import org.xaxaton.event_app.repo.EventRepo;
import org.xaxaton.event_app.repo.MemberRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("members/{memberId}/events")
public class EventsController extends BaseController<Event, EventDTO, EventRepo, EventMapper> {
    public EventsController(EventRepo repo, EventMapper mapper, MemberRepo memberRepo) {
        super(repo, mapper);
        this.memberRepo = memberRepo;
    }

    private final MemberRepo memberRepo;


    @GetMapping
    public ResponseEntity<List<EventDTO>> getAll(@PathVariable("memberId") int memberId) {
        List<Event> events = repo.findEventsByMemberId(memberId);
        List<EventDTO> dtos = mapper.toListOfDTOs(events);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EventDTO> createNew(@PathVariable("adminId") int adminId,
                                              @RequestBody EventDTO eventDTO) {
        Optional<Member> admin = memberRepo.findById(adminId);
        if (admin.isEmpty())
            return ResponseEntity.badRequest().build();
        Event event = mapper.toModel(eventDTO);
        if (repo.existsByName(event.getName()))
            return ResponseEntity.badRequest().build();

        event.setAdmin(admin.get());
        Event saved = repo.save(event);
        EventDTO savedDTO = mapper.toDTO(saved);
        return ResponseEntity.ok(savedDTO);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateById(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId") int eventId,
            @RequestBody EventDTO eventDTO) {


        Optional<Member> member = memberRepo.findById(memberId);
        if (member.isEmpty())
            return ResponseEntity.badRequest().build();

        Optional<Event> event = repo.findById(eventId);
        if (event.isEmpty())
            return ResponseEntity.badRequest().build();

        Member admin = event.get().getAdmin();

        if (!member.get().equals(admin))
            return ResponseEntity.status(403).build();

        Event existingEvent = event.get();

        if (repo.existsByName(eventDTO.getName()))
            return ResponseEntity.badRequest().build();

        existingEvent.setName(eventDTO.getName());
        existingEvent.setDateTime(eventDTO.getDateTime());
        existingEvent.setDescription(eventDTO.getDescription());
        existingEvent.setLatitude(eventDTO.getLatitude());
        existingEvent.setLongitude(eventDTO.getLongitude());

        Event updatedEvent = repo.save(existingEvent);

        EventDTO updatedDTO = mapper.toDTO(updatedEvent);
        return ResponseEntity.ok(updatedDTO);
    }
}