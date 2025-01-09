package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Todo;
import com.showcase.api.domain.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateTodoService {

	private final TodoRepository repository;

	public CreateTodoService(TodoRepository repository) {
		this.repository = repository;
	}

	public Todo execute(Todo newTodo) {
		log.info("inserting TODO {}", newTodo.toString());
		return repository.save(newTodo);
	}

}
