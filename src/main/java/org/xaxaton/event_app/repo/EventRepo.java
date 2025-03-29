package org.xaxaton.event_app.repo;

import org.springframework.stereotype.Repository;
import org.xaxaton.event_app.models.Event;

@Repository
public interface EventRepo extends BaseRepo<Event> {
    boolean existsByName(String name);
}
