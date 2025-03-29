package org.xaxaton.event_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

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

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "expected_price")
    @NotNull(message = "Expected price should not be null")
    private long expected_price;

    @Column(name = "real_price")
    //@NotNull(message = "Real price should not be null")
    private long real_price;

    @OneToOne
    @JoinColumn(name = "payer_id", referencedColumnName = "id")
    private Member payer;


    @ManyToOne
    @JoinColumn(name = "shopping_list_id", referencedColumnName = "id")
    private ShoppingList shoppingList;

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

    public Member getPayer() {
        return payer;
    }

    public void setPayer(Member payer) {
        this.payer = payer;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", description='" + getDescription() + '\'' +
                ", expected_price=" + getExpected_price() +
                ", real_price=" + getReal_price() +
                ", payer=" + getPayer() +
                ", shoppingList=" + getShoppingList() +
                '}';
    }
}
