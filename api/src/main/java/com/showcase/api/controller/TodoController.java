package com.showcase.api.controller;

import com.showcase.api.controller.data.mapper.TodoRestMapper;
import com.showcase.api.controller.data.request.TodoCreateRequest;
import com.showcase.api.controller.data.response.TodoCreateResponse;
import com.showcase.api.domain.service.CreateTodoService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

	private final TodoRestMapper todoMapper = Mappers.getMapper(TodoRestMapper.class);
	private final CreateTodoService createTodoService;

	public TodoController(CreateTodoService createTodoService) {
		this.createTodoService = createTodoService;
	}

	@PostMapping
	public ResponseEntity<TodoCreateResponse> createTodo(@RequestBody @Valid final TodoCreateRequest todoRequest) {
		var newTodo = todoMapper.toModel(todoRequest);
		var createdTodo = createTodoService.execute(newTodo);
		var todoResponse = todoMapper.toCreateResponse(createdTodo);

		return new ResponseEntity<>(todoResponse, HttpStatus.CREATED);
	}

}
