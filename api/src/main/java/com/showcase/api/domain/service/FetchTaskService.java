package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FetchTaskService {

	private final TaskRepository repository;

	public FetchTaskService(TaskRepository repository) {
		this.repository = repository;
	}

	public Page<Task> execute(Pageable pageable, Boolean completed) {
		Sort sort = Sort.by("createdAt").descending();
		Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

		return repository.findByFilter(completed, customPageable);
	}

}
