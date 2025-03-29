package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.Member;

@Repository
public interface MemberRepo extends BaseRepo<Member> {
}
