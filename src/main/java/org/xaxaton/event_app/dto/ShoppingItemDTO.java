package org.xaxaton.event_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ShoppingItemDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be in [2,30]")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    private String description;

    @NotNull(message = "Expected price should not be null")
    private Long expectedPrice;

    private Long realPrice;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private MemberDTO payer;

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

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public MemberDTO getPayer() {
        return payer;
    }

    public void setPayer(MemberDTO payer) {
        this.payer = payer;
    }

    @Override
    public String toString() {
        return "ShoppingItemDTO{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", description='" + getDescription() + '\'' +
                ", expectedPrice=" + getExpectedPrice() +
                ", realPrice=" + getRealPrice() +
                ", payer=" + getPayer() +
                '}';
    }
}
