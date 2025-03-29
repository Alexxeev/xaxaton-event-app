package org.xaxaton.event_app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xaxaton.event_app.dto.TaskDTO;
import org.xaxaton.event_app.models.Task;

@Component
public class TaskMapper extends BaseMapper<Task, TaskDTO> {
    public TaskMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
