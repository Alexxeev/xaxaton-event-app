package org.xaxaton.event_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xaxaton.event_app.dto.EventDTO;
import org.xaxaton.event_app.dto.TaskDTO;
import org.xaxaton.event_app.mappers.TaskMapper;
import org.xaxaton.event_app.models.Event;
import org.xaxaton.event_app.models.Task;
import org.xaxaton.event_app.repo.EventRepo;
import org.xaxaton.event_app.repo.MemberRepo;
import org.xaxaton.event_app.repo.TaskRepo;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getById(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @PathVariable("taskId") int taskId) {
        Optional<Task> task = taskRepo.findByTaskIdAndEventIdAndMemberId(taskId, eventId, memberId);
        if (task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TaskDTO taskDTO = taskMapper.toDTO(task.get());
        return ResponseEntity.ok(taskDTO);
    }



    @PostMapping
    public ResponseEntity<TaskDTO> createNew(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @RequestBody TaskDTO taskDTO) {
        if (!memberAndEventExists(memberId, eventId))
            return ResponseEntity.badRequest().build();
        var task = taskMapper.toModel(taskDTO);



        Optional<Event> event = eventRepo.findById(eventId);

        //System.out.println("Found event: " + event);
        if (event.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        task.setEvent(event.get());
        //System.out.println("to save task: " + task);

        task = taskRepo.save(task);
        var savedDTO = taskMapper.toDTO(task);
        return ResponseEntity.ok(savedDTO);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> update(
            @PathVariable("memberId") int memberId,
            @PathVariable("eventId")  int eventId,
            @PathVariable("taskId") int taskId,
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