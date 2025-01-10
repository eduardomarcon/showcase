package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateTaskServiceTest {

	private TaskRepository taskRepository;
	private CreateTaskService createTaskService;

	@BeforeEach
	void initialize() {
		taskRepository = mock(TaskRepository.class);
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	void shouldCreateATask() {
		var newTask = new Task();
		newTask.setTitle("title");
		newTask.setDescription("description");
		newTask.setCompleted(false);

		when(taskRepository.save(newTask)).thenReturn(newTask);

		var createdTask = createTaskService.execute(newTask);

		assertNotNull(createdTask);
	}

}
