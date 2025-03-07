package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FetchTaskServiceTest {

	private TaskRepository taskRepository;
	private FetchTaskService fetchTaskService;

	@BeforeEach
	void initialize() {
		taskRepository = mock(TaskRepository.class);
		fetchTaskService = new FetchTaskService(taskRepository);
	}

	@Test
	void shouldReturnTasksForGivenPageableWhenTasksExist() {
		Sort sort = Sort.by("createdAt");
		Pageable pageable = PageRequest.of(0, 2, sort);
		List<Task> tasks = Arrays.asList(
				Task.builder().id(1L).title("task 1").description("description 1").completed(false).build(),
				Task.builder().id(2L).title("task 2").description("description 2").completed(false).build());
		Page<Task> taskPage = new PageImpl<>(tasks, pageable, 2);

		when(taskRepository.findByFilter(false, pageable)).thenReturn(taskPage);

		Page<Task> result = fetchTaskService.execute(pageable, false);

		assertEquals(2, result.getContent().size());
		assertEquals(2, result.getTotalElements());
		assertEquals("task 1", result.getContent().get(0).getTitle());
	}

	@Test
	void shouldReturnEmptyPageWhenNoTasksExist() {
		Sort sort = Sort.by("createdAt");
		Pageable pageable = PageRequest.of(0, 2, sort);
		Page<Task> emptyPage = Page.empty(pageable);

		when(taskRepository.findByFilter(null, pageable)).thenReturn(emptyPage);

		Page<Task> result = fetchTaskService.execute(pageable, null);

		assertEquals(0, result.getContent().size());
		assertEquals(0, result.getTotalElements());
	}

	@Test
	void shouldReturnCorrectPageWhenMoreThanOnePageExists() {
		Sort sort = Sort.by("createdAt");
		Pageable pageable = PageRequest.of(1, 2, sort);
		List<Task> tasks = Arrays.asList(
				Task.builder().id(3L).title("task 3").description("description 3").completed(true).build(),
				Task.builder().id(4L).title("task 4").description("description 4").completed(false).build());
		Page<Task> taskPage = new PageImpl<>(tasks, pageable, 4);

		when(taskRepository.findByFilter(null, pageable)).thenReturn(taskPage);

		Page<Task> result = fetchTaskService.execute(pageable, null);

		assertEquals(2, result.getContent().size());
		assertEquals(4, result.getTotalElements());
		assertEquals("task 3", result.getContent().get(0).getTitle());
	}

}
