package com.showcase.api.controller;

import com.showcase.api.controller.data.request.TaskCreateRequest;
import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.model.User;
import com.showcase.api.domain.model.enums.UserRole;
import com.showcase.api.domain.service.CreateTaskService;
import com.showcase.api.domain.service.CreateUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerITTest extends ControllerCommonIT {

	@Autowired
	private CreateUserService createUserService;

	@Autowired
	private CreateTaskService createTaskService;

	@Test
	void shouldInsertATask() throws Exception {
		var taskCreateRequest = new TaskCreateRequest("title", "description", 1L);

		mockMvc
				.perform(post("/tasks")
								 .contentType(MediaType.APPLICATION_JSON)
								 .content(asJsonString(taskCreateRequest)))
				.andExpect(status().isCreated());
	}

	@Test
	void shouldUpdateTheCompletedFieldOfTheTask() throws Exception {
		var createdTask = insertANewTask();

		mockMvc
				.perform(patch("/tasks/" + createdTask.getId() + "/complete"))
				.andExpect(status().isOk());
	}

	@Test
	void shouldReturnTaskWhenIdExists() throws Exception {
		var createdTask = insertANewTask();

		mockMvc.perform(get("/tasks/{id}", createdTask.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.id").value(createdTask.getId()))
				.andExpect(jsonPath("$.data.title").value("title"))
				.andExpect(jsonPath("$.data.completed").value(true));
	}

	@Test
	void shouldReturnBadRequestWhenTaskNotFound() throws Exception {
		var taskId = 999L;

		mockMvc.perform(get("/tasks/{id}", taskId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.message").value("Task not found"));
	}

	@Test
	void shouldReturnPaginatedTasks() throws Exception {
		insertANewTask("Task 1");
		insertANewTask("Task 2");
		insertANewTask("Task 3");

		mockMvc.perform(get("/tasks?page=0&size=2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.content").isArray())
				.andExpect(jsonPath("$.data.content.length()").value(2))
				.andExpect(jsonPath("$.data.totalElements").value(3))
				.andExpect(jsonPath("$.data.totalPages").value(2))
				.andExpect(jsonPath("$.data.content[0].title").value("Task 1"))
				.andExpect(jsonPath("$.data.content[1].title").value("Task 2"));
	}

	private User insertANewUser() {
		var newUser = new User();
		newUser.setUsername("testUser");
		newUser.setPassword("securePassword");
		newUser.setEmail("testuser@example.com");
		newUser.setActive(true);
		newUser.setRole(UserRole.USER);

		return createUserService.execute(newUser);
	}

	private Task insertANewTask(String title) {
		var newTask = new Task();
		newTask.setTitle(title);
		newTask.setDescription("description");
		newTask.setCompleted(true);
		newTask.setUser(insertANewUser());
	
		return createTaskService.execute(newTask);
	}

	private Task insertANewTask() {
		return insertANewTask("title");
	}

}
