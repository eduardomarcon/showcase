package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateTaskService {

	private final TaskRepository repository;

	public CreateTaskService(TaskRepository repository) {
		this.repository = repository;
	}

	public Task execute(Task newTask) {
		log.info("inserting TASK {}", newTask.toString());
		return repository.save(newTask);
	}

}
