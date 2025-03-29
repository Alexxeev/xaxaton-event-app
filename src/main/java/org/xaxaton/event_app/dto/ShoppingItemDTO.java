package org.xaxaton.event_app.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ShoppingItemDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be in [2,30]")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    private String description;

    @NotNull(message = "Expected price should not be null")
    private long expected_price;

    //@NotNull(message = "Real price should not be null")
    private long real_price;

    private MemberDTO payer;

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
    public long getExpected_price() {
        return expected_price;
    }

    public void setExpected_price(@NotNull(message = "Expected price should not be null") long expected_price) {
        this.expected_price = expected_price;
    }

    @NotNull(message = "Real price should not be null")
    public long getReal_price() {
        return real_price;
    }

    public void setReal_price(@NotNull(message = "Real price should not be null") long real_price) {
        this.real_price = real_price;
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
                "name='" + getName() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", description='" + getDescription() + '\'' +
                ", expected_price=" + getExpected_price() +
                ", real_price=" + getReal_price() +
                ", payer=" + getPayer() +
                '}';
    }
}
