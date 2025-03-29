package org.xaxaton.event_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_member",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shopping_item_member",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_item_id")
    )
    private List<ShoppingItem> wishList;

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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<ShoppingItem> getWishList() {
        return wishList;
    }

    public void setWishList(List<ShoppingItem> wishList) {
        this.wishList = wishList;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", events=" + getEvents() +
                ", wishList=" + getWishList() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return getId() == member.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
