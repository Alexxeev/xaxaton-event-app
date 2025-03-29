package org.xaxaton.event_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xaxaton.event_app.dto.TaskDTO;
import org.xaxaton.event_app.mappers.TaskMapper;
import org.xaxaton.event_app.models.Task;
import org.xaxaton.event_app.repo.EventRepo;
import org.xaxaton.event_app.repo.MemberRepo;
import org.xaxaton.event_app.repo.TaskRepo;

import java.util.List;

@RestController
@RequestMapping("/members/{memberId}/events/{eventId}/tasks")
public class TasksController {
    private final TaskMapper taskMapper;
    private final TaskRepo taskRepo;
    private final MemberRepo memberRepo;
    private final EventRepo eventRepo;

    public TasksController(TaskRepo taskRepo, TaskMapper taskMapper, TaskMapper taskMapper1, MemberRepo memberRepo, EventRepo eventRepo) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper1;
        this.memberRepo = memberRepo;
        this.eventRepo = eventRepo;
    }

    private boolean memberAndEventExists(int memberId, int eventId) {
        if (!memberRepo.existsById(memberId))
            return false;
        return eventRepo.existsById(eventId);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId) {
        if (!memberAndEventExists(memberId, eventId))
            return ResponseEntity.badRequest().build();

        List<Task> tasks = taskRepo.findTasksByMemberIdAndEventId(memberId, eventId);

        if (tasks.isEmpty())
            return ResponseEntity.status(403).build();

        List<TaskDTO> dtos = taskMapper.toListOfDTOs(tasks);
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> createNew(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @RequestBody TaskDTO taskDTO) {
        if (!memberAndEventExists(memberId, eventId))
            return ResponseEntity.badRequest().build();
        var task = taskMapper.toModel(taskDTO);
        task = taskRepo.save(task);
        var savedDTO = taskMapper.toDTO(task);
        return ResponseEntity.ok(savedDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<TaskDTO> update(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @PathVariable("id") int taskId,
            @RequestBody TaskDTO taskDTO) {
        if (!memberAndEventExists(memberId, eventId))
            return ResponseEntity.badRequest().build();
        var taskOrNull = taskRepo.findById(taskId);
        if (taskOrNull.isEmpty())
            return ResponseEntity.badRequest().build();
        var task = taskOrNull.get();
        task.setName(taskDTO.getName());
        task.setDeadLine(taskDTO.getDeadLine());
        task.setDescription(taskDTO.getDescription());
        var savedDTO = taskMapper.toDTO(task);
        return ResponseEntity.ok(savedDTO);
    }
}