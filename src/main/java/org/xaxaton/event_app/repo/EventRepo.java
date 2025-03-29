package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.Event;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    boolean existsByName(String name);


    @Query("""
            SELECT e FROM Event e
            LEFT JOIN e.participants p
            WHERE e.admin.id = :memberId OR p.id = :memberId
            """)
    List<Event> findEventsByMemberId(@Param("memberId") int memberId);

    @Query(value = """
    SELECT * FROM event e
    WHERE e.id = :eventId
    AND (
        e.admin_id = :memberId
        OR EXISTS (
            SELECT 1 FROM event_member em
            WHERE em.event_id = e.id AND em.member_id = :memberId
        )
    )
    """, nativeQuery = true)
    Optional<Event> findByEventIdAndMemberId(@Param("eventId") int eventId, @Param("memberId") int memberId);


}
