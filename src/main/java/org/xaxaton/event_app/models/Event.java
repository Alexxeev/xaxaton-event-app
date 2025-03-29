package org.xaxaton.event_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be in [2,30]")
    private String name;


    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @Column(name="latitude")
    @NotNull(message = "Latitude should not be empty")
    private double latitude;

    @Column(name="longitude")
    @NotNull(message = "Longitude should not be empty")
    private double longitude;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Member admin;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_member",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> participants;

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

    public Member getAdmin() {
        return admin;
    }

    public void setAdmin(Member admin) {
        this.admin = admin;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Member> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Member> participants) {
        this.participants = participants;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", dateTime=" + getDateTime() +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                ", admin=" + getAdmin() +
                ", tasks=" + getTasks() +
                ", participants=" + getParticipants() +
                '}';
    }
}
