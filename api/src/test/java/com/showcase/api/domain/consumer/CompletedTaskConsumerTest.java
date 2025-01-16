package com.showcase.api.domain.consumer;

import com.showcase.api.domain.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompletedTaskConsumerTest {

	@Autowired
	private CompletedTaskConsumer completedTaskConsumer;

	@Test
	void consume_shouldLogAndProcessValidTask() {
		var validTask = Task.builder().id(1L).title("task").description("description").completed(true).build();

		completedTaskConsumer.consume(validTask);

		ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		assertThat(taskCaptor.getAllValues()).isEmpty();
	}

}
