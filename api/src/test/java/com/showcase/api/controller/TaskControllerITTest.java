package com.showcase.api.controller;

import com.showcase.api.controller.data.request.TaskCreateRequest;
import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.service.CreateTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerITTest extends ControllerCommonIT {

	@Autowired
	private CreateTaskService createTaskService;

	@Test
	void shouldInsertATask() throws Exception {
		var taskCreateRequest = new TaskCreateRequest("title", "description");

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
				.perform(patch("/tasks/" + createdTask.getId() + "/completed"))
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

	private Task insertANewTask() {
		var newTask = new Task();
		newTask.setTitle("title");
		newTask.setDescription("description");
		newTask.setCompleted(true);

		return createTaskService.execute(newTask);
	}

}
