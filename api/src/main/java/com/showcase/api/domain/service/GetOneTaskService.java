package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import com.showcase.api.support.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetOneTaskService {

	private final TaskRepository repository;

	public GetOneTaskService(TaskRepository repository) {
		this.repository = repository;
	}

	public Task byId(Long id) {
		log.info("getting TASK {}", id);

		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
	}

}
