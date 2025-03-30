package org.xaxaton.event_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Invite {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "invitee_id", referencedColumnName = "id")
    private Member invitee;

    @ManyToOne
    @JoinColumn(name = "inviter_id", referencedColumnName = "id")
    private Member inviter;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @Column(name = "is_accepted")
    @ColumnDefault("false")
    private boolean isAccepted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getInvitee() {
        return invitee;
    }

    public void setInvitee(Member invitee) {
        this.invitee = invitee;
    }

    public Member getInviter() {
        return inviter;
    }

    public void setInviter(Member inviter) {
        this.inviter = inviter;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
