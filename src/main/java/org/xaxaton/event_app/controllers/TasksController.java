package org.xaxaton.event_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xaxaton.event_app.dto.TaskDTO;
import org.xaxaton.event_app.mappers.TaskMapper;
import org.xaxaton.event_app.models.Task;
import org.xaxaton.event_app.repo.TaskRepo;

@RestController
@RequestMapping("/tasks")
public class TasksController extends BaseController<Task, TaskDTO, TaskRepo, TaskMapper> {
    public TasksController(TaskRepo repo, TaskMapper mapper) {
        super(repo, mapper);
    }
}
