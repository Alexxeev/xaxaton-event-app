package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xaxaton.event_app.models.Member;

public interface MemberRepo extends JpaRepository<Member, Integer> {

}
