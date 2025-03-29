package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.TaskDTO;
import org.xaxaton.event_app.dto.TaskDTO;
import org.xaxaton.event_app.models.Task;
import org.xaxaton.event_app.models.Task;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    private final ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TaskDTO toDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task toModel(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }

    public List<TaskDTO> toListOfDTOs(List<Task> tasks) {
        List<TaskDTO> result = new ArrayList<>();

        for (Task task : tasks) {
            result.add(toDTO(task));
        }

        return result;
    }

    public List<Task> toListOfModels(List<TaskDTO> taskDTOs) {
        List<Task> result = new ArrayList<>();

        for (TaskDTO taskDTO : taskDTOs) {
            result.add(toModel(taskDTO));
        }

        return result;
    }
}
