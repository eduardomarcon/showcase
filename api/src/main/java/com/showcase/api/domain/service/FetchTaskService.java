package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FetchTaskService {

	private final TaskRepository repository;

	public FetchTaskService(TaskRepository repository) {
		this.repository = repository;
	}

	public Page<Task> execute(Pageable pageable) {
		log.info("Fetching TASKs with pagination: page number = {}, page size = {}", pageable.getPageNumber(),
				 pageable.getPageSize());
		Page<Task> tasks = repository.findAll(pageable);
		log.info("Fetched {} task(s), total elements = {}", tasks.getContent().size(), tasks.getTotalElements());
		return tasks;
	}

}
