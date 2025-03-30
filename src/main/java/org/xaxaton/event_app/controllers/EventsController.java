package org.xaxaton.event_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xaxaton.event_app.dto.Debt;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.dto.InviteRequestDTO;
import org.xaxaton.event_app.dto.InviteResponseDTO;
import org.xaxaton.event_app.mappers.EventMapper;
import org.xaxaton.event_app.mappers.MemberMapper;
import org.xaxaton.event_app.mappers.ShoppingItemMapper;
import org.xaxaton.event_app.models.Event;
import org.xaxaton.event_app.models.Invite;
import org.xaxaton.event_app.models.Member;
import org.xaxaton.event_app.models.ShoppingItem;
import org.xaxaton.event_app.repo.EventRepo;
import org.xaxaton.event_app.repo.InviteRepo;
import org.xaxaton.event_app.repo.MemberRepo;
import org.xaxaton.event_app.repo.ShoppingItemRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("members/{memberId}/events")
public class EventsController {
    private final MemberRepo memberRepo;
    private final EventRepo eventRepo;
    private final InviteRepo inviteRepo;
    private final MemberMapper memberMapper;
    private final ShoppingItemMapper shoppingItemMapper;
    private final EventMapper eventMapper;
    private final ShoppingItemRepo shoppingItemRepo;

    public EventsController(MemberRepo memberRepo, EventRepo eventRepo, InviteRepo inviteRepo, MemberMapper memberMapper, ShoppingItemMapper shoppingItemMapper, EventMapper eventMapper, ShoppingItemRepo shoppingItemRepo) {
        this.memberRepo = memberRepo;
        this.eventRepo = eventRepo;
        this.inviteRepo = inviteRepo;
        this.memberMapper = memberMapper;
        this.shoppingItemMapper = shoppingItemMapper;
        this.eventMapper = eventMapper;
        this.shoppingItemRepo = shoppingItemRepo;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAll(@PathVariable("memberId") int memberId) {
        List<Event> events = eventRepo.findEventsByMemberId(memberId);
        List<EventDTO> dtos = eventMapper.toListOfDTOs(events);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getById(@PathVariable int memberId,
                                            @PathVariable int eventId) {
        Optional<Event> event = eventRepo.findByEventIdAndMemberId(eventId, memberId);
        if (event.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        EventDTO eventDTO = eventMapper.toDTO(event.get());
        return ResponseEntity.ok(eventDTO);
    }

    @PostMapping
    public ResponseEntity<EventDTO> createNew(@PathVariable("memberId") int adminId,
                                              @RequestBody EventDTO eventDTO) {
        Optional<Member> admin = memberRepo.findById(adminId);
        if (admin.isEmpty())
            return ResponseEntity.badRequest().build();
        Event event = eventMapper.toModel(eventDTO);
        if (eventRepo.existsByName(event.getName()))
            return ResponseEntity.badRequest().build();

        event.setAdmin(admin.get());
        Event saved = eventRepo.save(event);
        EventDTO savedDTO = eventMapper.toDTO(saved);
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

        Optional<Event> event = eventRepo.findById(eventId);
        if (event.isEmpty())
            return ResponseEntity.badRequest().build();

        Member admin = event.get().getAdmin();

        if (!member.get().equals(admin))
            return ResponseEntity.status(403).build();

        Event existingEvent = event.get();

        if (eventRepo.existsByName(eventDTO.getName()))
            return ResponseEntity.badRequest().build();

        existingEvent.setName(eventDTO.getName());
        existingEvent.setDateTime(eventDTO.getDateTime());
        existingEvent.setDescription(eventDTO.getDescription());
        existingEvent.setLatitude(eventDTO.getLatitude());
        existingEvent.setLongitude(eventDTO.getLongitude());

        Event updatedEvent = eventRepo.save(existingEvent);

        EventDTO updatedDTO = eventMapper.toDTO(updatedEvent);
        return ResponseEntity.ok(updatedDTO);
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<InviteResponseDTO> invite(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId") int eventId,
            @PathVariable("id") int id,
            @RequestBody InviteRequestDTO inviteRequestDTO) {
        Invite invite = new Invite();

        Member invitee = memberRepo.getReferenceById(inviteRequestDTO.inviteeId());
        invite.setInvitee(invitee);
        Member inviter = memberRepo.getReferenceById(memberId);
        invite.setInviter(inviter);
        Event event = eventRepo.getReferenceById(eventId);
        invite.setEvent(event);
        invite = inviteRepo.save(invite);
        return ResponseEntity.ok(
                new InviteResponseDTO("/invites/" + invite.getId()));
    }


    /*
    @GetMapping("/{eventId}/debts")
    public ResponseEntity<List<Debt>> getAllDebts(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId") int eventId) {
        Event event = eventRepo.findById(eventId).get();
        List<ShoppingItem> items = shoppingItemRepo.findAllByMemberIdAnnEventId(memberId, eventId);
        Map<Integer, Long> debts = new HashMap<>();
        for (ShoppingItem item : items) {
            List<Member> wishers = item.getMembersWishingThis();

            int wishersCount = wishers.size();
            boolean isMemberInWishers = wishers.stream()
                    .anyMatch(wisher -> wisher.getId() == memberId);
            long prevSum = debts.getOrDefault(item.getPayer().getId(), 0L);
            long debtSum = 0L;
            if (isMemberInWishers && wishersCount == 1) {
                debtSum = item.getRealPrice();
                debts.put(item.getPayer().getId(), prevSum + item.getRealPrice());
            } else if (isMemberInWishers) {
                debtSum = item.getRealPrice() / wishersCount;
            } else if (wishersCount == 0) {
                debtSum = item.getRealPrice() / (event.getParticipants().size() + 1);
            }
            debts.put(item.getPayer().getId(), prevSum + debtSum);
        }
        var debtList = debts.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> new Debt(
                        memberMapper.toDTO(
                                memberRepo.findById(entry.getKey()).get()),
                        entry.getValue()))
                .toList();
        return ResponseEntity.ok(debtList);
    }*/
}
