package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.ShoppingItem;

@Repository
public interface ShoppingItemRepo extends JpaRepository<ShoppingItem, Integer> {
    /*@Query("""
            select si from shopping-item si
            left join task t
            where t.id = :taskId
            """)
    List<ShoppingItem> findAllByTaskId(int taskId);*/
}
