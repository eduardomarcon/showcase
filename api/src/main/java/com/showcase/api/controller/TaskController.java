package com.showcase.api.controller;

import com.showcase.api.controller.data.mapper.TaskRestMapper;
import com.showcase.api.controller.data.request.TaskCreateRequest;
import com.showcase.api.controller.data.response.TaskCreateResponse;
import com.showcase.api.domain.service.CreateTaskService;
import com.showcase.api.domain.service.UpdateCompletedTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Tasks", description = "Task handling services")
@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskRestMapper taskMapper = Mappers.getMapper(TaskRestMapper.class);
	private final CreateTaskService createTaskService;
	private final UpdateCompletedTaskService updateCompletedTaskService;

	public TaskController(CreateTaskService createTaskService, UpdateCompletedTaskService updateCompletedTaskService) {
		this.createTaskService = createTaskService;
		this.updateCompletedTaskService = updateCompletedTaskService;
	}

	@PostMapping
	@Operation(summary = "Create a new task", description = "Creates a new task")
	@ApiResponse(responseCode = "201", description = "Task created successfully")
	@ApiResponse(responseCode = "400", description = "Invalid task data")
	public ResponseEntity<TaskCreateResponse> createTask(@RequestBody @Valid final TaskCreateRequest taskCreateRequest) {
		var newTask = taskMapper.toModel(taskCreateRequest);
		var createdTask = createTaskService.execute(newTask);
		var taskCreateResponse = taskMapper.toCreateResponse(createdTask);

		return new ResponseEntity<>(taskCreateResponse, HttpStatus.CREATED);
	}

	@PatchMapping("{id}/completed")
	@Operation(summary = "Update a task", description = "Updates the completed field of the task with the given ID.")
	@Parameter(name = "id", description = "The ID of the task", required = true, schema = @Schema(type = "integer", format = "int64"))
	@ApiResponse(responseCode = "200", description = "Task updated successfully")
	@ApiResponse(responseCode = "400", description = "Task not found", content = @Content)
	public ResponseEntity<Void> completeTask(@PathVariable final Long id) {
		updateCompletedTaskService.execute(id);
		return ResponseEntity.ok().build();
	}

}
