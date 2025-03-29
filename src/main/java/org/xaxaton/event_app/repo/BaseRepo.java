package org.xaxaton.event_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface BaseRepo<M> extends JpaRepository<M, Integer> {
}
