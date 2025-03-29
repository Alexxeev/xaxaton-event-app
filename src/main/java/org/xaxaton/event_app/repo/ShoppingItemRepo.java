package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.ShoppingItem;

import java.util.List;

@Repository
public interface ShoppingItemRepo extends BaseRepo<ShoppingItem> {
    @Query("""
            select si from shopping-item si
            left join task t
            where t.id = :taskId
            """)
    List<ShoppingItem> findAllByTaskId(int taskId);
}
