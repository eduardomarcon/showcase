package com.showcase.api.domain.service;

import com.showcase.api.domain.repository.TaskRepository;
import com.showcase.api.support.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UpdateCompletedTaskService {

	private final TaskRepository repository;

	public UpdateCompletedTaskService(TaskRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@CacheEvict(value = "tasks", key = "#id")
	public void execute(Long id) {
		log.info("updating completed TASK {}", id);
		var foundTask = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));

		foundTask.setCompleted(!foundTask.isCompleted());
		repository.save(foundTask);
	}

}
