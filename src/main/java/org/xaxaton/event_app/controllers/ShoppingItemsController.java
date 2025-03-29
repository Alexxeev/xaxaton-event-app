package org.xaxaton.event_app.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xaxaton.event_app.dto.ShoppingItemDTO;
import org.xaxaton.event_app.dto.TaskDTO;
import org.xaxaton.event_app.mappers.ShoppingItemMapper;
import org.xaxaton.event_app.models.ShoppingItem;
import org.xaxaton.event_app.models.Task;
import org.xaxaton.event_app.repo.EventRepo;
import org.xaxaton.event_app.repo.MemberRepo;
import org.xaxaton.event_app.repo.ShoppingItemRepo;
import org.xaxaton.event_app.repo.TaskRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members/{memberId}/events/{eventId}/tasks/{taskId}/shopping-items")
public class ShoppingItemsController {

    private final ShoppingItemMapper shoppingItemMapper;
    private final ShoppingItemRepo shoppingItemRepo;
    private final TaskRepo taskRepo;
    private final EventRepo eventRepo;
    private final MemberRepo memberRepo;


    public ShoppingItemsController(ShoppingItemMapper shoppingItemMapper, ShoppingItemRepo shoppingItemRepo, TaskRepo taskRepo, EventRepo eventRepo, MemberRepo memberRepo) {
        this.shoppingItemMapper = shoppingItemMapper;
        this.shoppingItemRepo = shoppingItemRepo;
        this.taskRepo = taskRepo;
        this.eventRepo = eventRepo;
        this.memberRepo = memberRepo;
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
        var shoppingItems = shoppingItemRepo.findAll();
        //var shoppingItems = repo.findAllByTaskId(taskId);
        var dtos = shoppingItemMapper.toListOfDTOs(shoppingItems);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{shoppingItemId}")
    public ResponseEntity<ShoppingItemDTO> getById(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId") int eventId,
            @PathVariable("taskId") int taskId,
            @PathVariable("shoppingItemId") int shoppingItemId) {

        Optional<Task> task = taskRepo.findByTaskIdAndEventIdAndMemberId(taskId, eventId, memberId);
        if (task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Optional<ShoppingItem> shoppingItem = task.get().getShoppingItems()
                .stream()
                .filter(item -> item.getId() == shoppingItemId)
                .findFirst();

        if (shoppingItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ShoppingItemDTO shoppingItemDTO = shoppingItemMapper.toDTO(shoppingItem.get());
        return ResponseEntity.ok(shoppingItemDTO);

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
        var item = shoppingItemMapper.toModel(shoppingItemDTO);
        item = shoppingItemRepo.save(item);
        var newDto = shoppingItemMapper.toDTO(item);
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
        var itemOrNull = shoppingItemRepo.findById(itemId);
        if (itemOrNull.isEmpty())
            return ResponseEntity.notFound().build();
        var item = itemOrNull.get();
        item.setDescription(shoppingItemDTO.getDescription());
        item.setName(shoppingItemDTO.getName());
        var payerOrNull = memberRepo.findById(shoppingItemDTO.getPayer().getId());
        payerOrNull.ifPresent(item::setPayer);
        item.setExpectedPrice(shoppingItemDTO.getExpectedPrice());
        item.setRealPrice(shoppingItemDTO.getRealPrice());
        item = shoppingItemRepo.save(item);
        var updatedDto = shoppingItemMapper.toDTO(item);
        return ResponseEntity.ok(updatedDto);
    }
}
