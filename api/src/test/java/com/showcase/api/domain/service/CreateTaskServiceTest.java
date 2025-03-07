package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.model.User;
import com.showcase.api.domain.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateTaskServiceTest {

	private TaskRepository taskRepository;
	private GetOneUserService getUserService;
	private CreateTaskService createTaskService;

	@BeforeEach
	void initialize() {
		taskRepository = mock(TaskRepository.class);
		getUserService = mock(GetOneUserService.class);
		createTaskService = new CreateTaskService(taskRepository, getUserService);
	}

	@Test
	void shouldCreateATask() {
		var newUser = new User();
		newUser.setId(1L);

		var newTask = new Task();
		newTask.setTitle("title");
		newTask.setDescription("description");
		newTask.setCompleted(false);
		newTask.setUser(newUser);

		when(taskRepository.save(newTask)).thenReturn(newTask);

		var createdTask = createTaskService.execute(newTask);

		assertNotNull(createdTask);

		verify(getUserService).byId(newUser.getId());
	}

}
