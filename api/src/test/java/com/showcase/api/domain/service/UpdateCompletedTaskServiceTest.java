package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import com.showcase.api.support.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateCompletedTaskServiceTest {

	private TaskRepository taskRepository;
	private UpdateCompletedTaskService updateCompletedTaskService;
	private SendMessageService sendMessageService;

	@BeforeEach
	void initialize() {
		taskRepository = mock(TaskRepository.class);
		sendMessageService = mock(SendMessageService.class);
		updateCompletedTaskService = new UpdateCompletedTaskService(taskRepository, sendMessageService);
	}

	@Test
	void shouldUpdateTheCompletedFieldOfTheTask() {
		var idTask = 1L;
		var mockTask = new Task();
		mockTask.setId(idTask);
		mockTask.setCompleted(false);

		when(taskRepository.findById(idTask)).thenReturn(Optional.of(mockTask));

		updateCompletedTaskService.execute(idTask);

		verify(taskRepository).save(mockTask);
		verify(sendMessageService).taskCompleted(mockTask);
	}

	@Test
	void shouldNotUpdateTheCompletedFieldOfTheTask() {
		var idTask = 1L;

		when(taskRepository.findById(idTask)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> updateCompletedTaskService.execute(idTask));

		verify(sendMessageService, never()).taskCompleted(any());
	}

}
