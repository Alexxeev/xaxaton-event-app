package org.xaxaton.event_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MemberDTO {

    @NotEmpty(message = "Spot name should not be empty")
    @Size(min = 2, max = 30, message = "Spot name length should be in [2,30]")
    private String name;

    public @NotEmpty(message = "Spot name should not be empty") @Size(min = 2, max = 30, message = "Spot name length should be in [2,30]") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Spot name should not be empty") @Size(min = 2, max = 30, message = "Spot name length should be in [2,30]") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
