package org.xaxaton.event_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TaskDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be in [2,30]")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deadLine;

    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name length should be in [2,30]") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name length should be in [2,30]") String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", deadLine=" + getDeadLine() +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
