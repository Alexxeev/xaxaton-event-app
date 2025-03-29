package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.Task;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t " +
            "JOIN t.event e " +
            "LEFT JOIN e.participants p " +
            "WHERE e.id = :eventId " +
            "AND (e.admin.id = :memberId OR p.id = :memberId)")
    List<Task> findTasksByMemberIdAndEventId(@Param("memberId") int memberId,
                                             @Param("eventId") int eventId);


    @Query("SELECT COUNT(e) > 0 FROM Event e " +
            "LEFT JOIN e.participants p " +
            "WHERE e.id = :eventId " +
            "AND (e.admin.id = :memberId OR p.id = :memberId)")
    boolean existsByEventIdAndMemberId(@Param("eventId") int eventId,
                                       @Param("memberId") int memberId);
}
