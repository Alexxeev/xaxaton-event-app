package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xaxaton.event_app.models.Invite;

public interface InviteRepo extends JpaRepository<Invite, Integer> {
}
