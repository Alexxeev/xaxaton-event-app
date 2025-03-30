package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.ShoppingItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingItemRepo extends JpaRepository<ShoppingItem, Integer> {
    /*@Query("""
            select si from shopping-item si
            left join task t
            where t.id = :taskId
            """)
    List<ShoppingItem> findAllByTaskId(int taskId);*/


    @Query(value = """
    SELECT * FROM
    shopping_item si
    JOIN task ON si.task_id=task.id
    JOIN event ON task.event_id = event.id
    LEFT JOIN event_member ON event_member.event_id = event.id
    LEFT JOIN member ON event_member.member_id = member.id
    WHERE
    si.id = :shoppingItemId
    AND task.id = :taskId
    AND event.id = :eventId
    AND (member.id = :memberId OR event.admin_id=:memberId)
    """, nativeQuery = true)
    Optional<ShoppingItem> findByShoppingItemIdAndTaskIdAndEventIdAndMemberId(
            @Param("shoppingItemId") int shoppingItemId,
            @Param("taskId") int taskId,
            @Param("eventId") int eventId,
            @Param("memberId") int memberId);

    List<ShoppingItem> findAllByMemberIdAnnEventId(
            @Param("memberId") int memberId,
            @Param("eventId") int eventId);
}
