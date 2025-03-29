package org.xaxaton.event_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class EventDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be in [2,30]")
    private String name;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @NotNull(message = "Latitude should not be empty")
    private double latitude;

    @NotNull(message = "Longitude should not be empty")
    private double longitude;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private MemberDTO admin;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @NotNull(message = "Latitude should not be empty")
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(@NotNull(message = "Latitude should not be empty") double latitude) {
        this.latitude = latitude;
    }

    @NotNull(message = "Longitude should not be empty")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(@NotNull(message = "Longitude should not be empty") double longitude) {
        this.longitude = longitude;
    }

    public MemberDTO getAdmin() {
        return admin;
    }

    public void setAdmin(MemberDTO admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", dateTime=" + getDateTime() +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                ", admin=" + getAdmin() +
                '}';
    }
}
