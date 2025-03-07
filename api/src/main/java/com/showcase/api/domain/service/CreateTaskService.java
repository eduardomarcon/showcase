package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateTaskService {

	private final TaskRepository repository;
	private final GetOneUserService getOneUserService;

	public CreateTaskService(TaskRepository repository, GetOneUserService getOneUserService) {
		this.repository = repository;
		this.getOneUserService = getOneUserService;
	}

	public Task execute(Task newTask) {
		getOneUserService.byId(newTask.getUser().getId());
		log.info("inserting TASK {}", newTask);
		return repository.save(newTask);
	}

}
