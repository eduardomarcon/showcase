package com.showcase.api.controller;

import com.showcase.api.controller.data.mapper.TaskRestMapper;
import com.showcase.api.controller.data.request.TaskCreateRequest;
import com.showcase.api.controller.data.response.TaskCreateResponse;
import com.showcase.api.domain.service.CreateTaskService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskRestMapper taskMapper = Mappers.getMapper(TaskRestMapper.class);
	private final CreateTaskService createTaskService;

	public TaskController(CreateTaskService createTaskService) {
		this.createTaskService = createTaskService;
	}

	@PostMapping
	public ResponseEntity<TaskCreateResponse> createTask(@RequestBody @Valid final TaskCreateRequest taskCreateRequest) {
		var newTask = taskMapper.toModel(taskCreateRequest);
		var createdTask = createTaskService.execute(newTask);
		var taskCreateResponse = taskMapper.toCreateResponse(createdTask);

		return new ResponseEntity<>(taskCreateResponse, HttpStatus.CREATED);
	}

}
