package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import com.showcase.api.support.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetOneTaskServiceTest {

	private TaskRepository taskRepository;
	private GetOneTaskService getOneTaskService;

	@BeforeEach
	void initialize() {
		taskRepository = mock(TaskRepository.class);
		getOneTaskService = new GetOneTaskService(taskRepository);
	}

	@Test
	void shouldReturnTaskWhenTaskExists() {
		var taskId = 1L;
		var expectedTask = Task.builder()
				.id(taskId).title("sample task")
				.description("description of the task")
				.completed(false).build();

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(expectedTask));

		var actualTask = getOneTaskService.byId(taskId);

		assertEquals(expectedTask, actualTask);
	}

	@Test
	void shouldThrowEntityNotFoundExceptionWhenTaskDoesNotExist() {
		var taskId = 1L;
		when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> getOneTaskService.byId(taskId));
	}

}
