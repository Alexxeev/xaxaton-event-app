package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t " +
            "JOIN t.event e " +
            "LEFT JOIN e.participants p " +
            "WHERE e.id = :eventId " +
            "AND (e.admin.id = :memberId OR p.id = :memberId)")
    List<Task> findTasksByMemberIdAndEventId(@Param("memberId") int memberId,
                                             @Param("eventId") int eventId);


    @Query(value = """
    SELECT t.*
    FROM task t
    JOIN event e ON e.id = t.event_id
    LEFT JOIN event_member em ON em.event_id = e.id AND em.member_id = :memberId
    WHERE t.id = :taskId
    AND e.id = :eventId
    AND (e.admin_id = :memberId OR em.member_id IS NOT NULL)
    """, nativeQuery = true)
    Optional<Task> findByTaskIdAndEventIdAndMemberId(
            @Param("taskId") int taskId,
            @Param("eventId") int eventId,
            @Param("memberId") int memberId
    );

    @Query("SELECT COUNT(e) > 0 FROM Event e " +
            "LEFT JOIN e.participants p " +
            "WHERE e.id = :eventId " +
            "AND (e.admin.id = :memberId OR p.id = :memberId)")
    boolean existsByEventIdAndMemberId(@Param("eventId") int eventId,
                                       @Param("memberId") int memberId);
}
