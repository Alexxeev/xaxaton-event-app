package org.xaxaton.event_app.dto;

import java.util.Objects;

public final class InviteDTO {
    private int id;
    private EventDTO event;
    private MemberDTO inviter;
    private MemberDTO invitee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public MemberDTO getInviter() {
        return inviter;
    }

    public void setInviter(MemberDTO inviter) {
        this.inviter = inviter;
    }

    public MemberDTO getInvitee() {
        return invitee;
    }

    public void setInvitee(MemberDTO invitee) {
        this.invitee = invitee;
    }

    @Override
    public String toString() {
        return "InviteDTO[" +
                "id=" + id + ", " +
                "event=" + event + ", " +
                "inviter=" + inviter + ", " +
                "invitee=" + invitee + ']';
    }

}
