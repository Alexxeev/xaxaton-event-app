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

    @NotNull(message = "Real price should not be null")
    private long real_price;

    private MemberDTO payer;


}
