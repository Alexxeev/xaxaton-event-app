package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepo<M> extends JpaRepository<M, Integer> {
}
