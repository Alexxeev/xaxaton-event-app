package org.xaxaton.event_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.TaskDTO;
import org.xaxaton.event_app.mappers.TaskMapper;
import org.xaxaton.event_app.models.Task;
import org.xaxaton.event_app.repo.EventRepo;
import org.xaxaton.event_app.repo.MemberRepo;
import org.xaxaton.event_app.repo.TaskRepo;

import java.util.List;

@RestController
@RequestMapping("/members/{memberId}/events/{eventId}/tasks")
public class TasksController extends BaseController<Task, TaskDTO, TaskRepo, TaskMapper> {
    private final MemberRepo memberRepo;
    private final EventRepo eventRepo;

    public TasksController(TaskRepo repo, TaskMapper mapper, MemberRepo memberRepo, EventRepo eventRepo) {
        super(repo, mapper);
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
        if (memberAndEventExists(memberId, eventId))
            return ResponseEntity.badRequest().build();
        var tasks = repo.findAll();
        var dtos = mapper.toListOfDTOs(tasks);
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> createNew(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @RequestBody TaskDTO taskDTO) {
        if (!memberAndEventExists(memberId, eventId))
            return ResponseEntity.badRequest().build();
        var task = mapper.toModel(taskDTO);
        task = repo.save(task);
        var savedDTO = mapper.toDTO(task);
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
        var taskOrNull = repo.findById(taskId);
        if (taskOrNull.isEmpty())
            return ResponseEntity.badRequest().build();
        var task = taskOrNull.get();
        task.setName(taskDTO.getName());
        task.setDeadLine(taskDTO.getDeadLine());
        task.setDescription(taskDTO.getDescription());
        var savedDTO = mapper.toDTO(task);
        return ResponseEntity.ok(savedDTO);
    }
}
