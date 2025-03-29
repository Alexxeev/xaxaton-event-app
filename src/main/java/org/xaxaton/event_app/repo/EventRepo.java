package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.Event;

import java.util.List;

@Repository
public interface EventRepo extends BaseRepo<Event> {
    boolean existsByName(String name);


    @Query("SELECT e FROM Event e " +
            "LEFT JOIN e.participants p " +
            "WHERE e.admin.id = :memberId OR p.id = :memberId")
    List<Event> findEventsByMemberId(@Param("memberId") int memberId);
}
