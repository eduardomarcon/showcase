package com.showcase.api.domain.repository;

import com.showcase.api.domain.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	default Page<Task> findByFilter(Boolean completed, Pageable pageable) {
		if (completed == null) return findAll(pageable);

		return findByCompleted(completed, pageable);
	}

	Page<Task> findByCompleted(boolean completed, Pageable pageable);

}
