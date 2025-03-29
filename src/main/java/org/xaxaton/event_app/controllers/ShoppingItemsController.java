package org.xaxaton.event_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.ShoppingItemDTO;
import org.xaxaton.event_app.mappers.ShoppingItemMapper;

import org.xaxaton.event_app.models.ShoppingItem;
import org.xaxaton.event_app.repo.EventRepo;
import org.xaxaton.event_app.repo.MemberRepo;
import org.xaxaton.event_app.repo.ShoppingItemRepo;
import org.xaxaton.event_app.repo.TaskRepo;

import java.util.List;


@RestController
@RequestMapping("/members/{memberId}/events/{eventId}/tasks/{tasksId}/shopping-items")
public class ShoppingItemsController extends BaseController<ShoppingItem,
        ShoppingItemDTO,
        ShoppingItemRepo,
        ShoppingItemMapper> {
    private final MemberRepo memberRepo;
    private final EventRepo eventRepo;
    private final TaskRepo taskRepo;

    public ShoppingItemsController(ShoppingItemRepo repo, ShoppingItemMapper mapper, MemberRepo memberRepo, EventRepo eventRepo, TaskRepo taskRepo) {
        super(repo, mapper);
        this.memberRepo = memberRepo;
        this.eventRepo = eventRepo;
        this.taskRepo = taskRepo;
    }

    private boolean memberAndEventAndTaskExists(int memberId, int eventId, int taskId) {
        if (!memberRepo.existsById(memberId))
            return false;
        if (!eventRepo.existsById(eventId))
            return false;
        return taskRepo.existsById(taskId);
    }

    @GetMapping
    public ResponseEntity<List<ShoppingItemDTO>> getAll(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @PathVariable("taskId")    int taskId) {
        if (!memberAndEventAndTaskExists(memberId, eventId, taskId))
            return ResponseEntity.notFound().build();
        //if not in event or task return 403
        var shoppingItems = repo.findAll();
        //var shoppingItems = repo.findAllByTaskId(taskId);
        var dtos = mapper.toListOfDTOs(shoppingItems);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ShoppingItemDTO> createNew(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @PathVariable("taskId")    int taskId,
            @RequestBody ShoppingItemDTO shoppingItemDTO) {
        if (!memberAndEventAndTaskExists(memberId, eventId, taskId))
            return ResponseEntity.notFound().build();
        //if not in event or task return 403
        var item = mapper.toModel(shoppingItemDTO);
        item = repo.save(item);
        var newDto = mapper.toDTO(item);
        return ResponseEntity.ok(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingItemDTO> update(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @PathVariable("taskId")    int taskId,
            @PathVariable("id")       int itemId,
            @RequestBody ShoppingItemDTO shoppingItemDTO) {
        if (!memberAndEventAndTaskExists(memberId, eventId, taskId))
            return ResponseEntity.notFound().build();
        var itemOrNull = repo.findById(itemId);
        if (itemOrNull.isEmpty())
            return ResponseEntity.notFound().build();
        var item = itemOrNull.get();
        item.setDescription(shoppingItemDTO.getDescription());
        item.setName(shoppingItemDTO.getName());
        var payerOrNull = memberRepo.findById(shoppingItemDTO.getPayer().getId());
        payerOrNull.ifPresent(item::setPayer);
        item.setExpectedPrice(shoppingItemDTO.getExpectedPrice());
        item.setRealPrice(shoppingItemDTO.getRealPrice());
        item = repo.save(item);
        var updatedDto = mapper.toDTO(item);
        return ResponseEntity.ok(updatedDto);
    }
    
}

