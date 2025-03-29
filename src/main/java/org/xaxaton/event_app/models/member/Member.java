package org.xaxaton.event_app.models.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be in [2,30]")
    private String name;

    @NotNull(message = "Name should not be null")
    private MemberRole memberRole;

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

    public @NotNull(message = "Name should not be null") MemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(@NotNull(message = "Name should not be null") MemberRole memberRole) {
        this.memberRole = memberRole;
    }


}
