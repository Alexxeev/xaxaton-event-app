package org.xaxaton.event_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shopping_item")
public class ShoppingItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be in [2,30]")
    private String name;

    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "expectedPrice")
    @NotNull(message = "Expected price should not be null")
    private Long expectedPrice;

    @Column(name = "realPrice")
    private Long realPrice;

    @OneToOne
    @JoinColumn(name = "payer_id", referencedColumnName = "id")
    private Member payer;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shopping_item_member",
            joinColumns = @JoinColumn(name = "shopping_item_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> membersWishingThis;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Expected price should not be null")
    public Long getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(@NotNull(message = "Expected price should not be null") Long expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    //@NotNull(message = "Real price should not be null")
    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(@NotNull(message = "Real price should not be null") Long realPrice) {
        this.realPrice = realPrice;
    }

    public Member getPayer() {
        return payer;
    }

    public void setPayer(Member payer) {
        this.payer = payer;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Member> getMembersWishingThis() {
        return membersWishingThis;
    }

    public void setMembersWishingThis(List<Member> membersWishingThis) {
        this.membersWishingThis = membersWishingThis;
    }


}
