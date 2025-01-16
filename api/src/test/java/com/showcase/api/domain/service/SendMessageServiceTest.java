package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.support.util.KafkaConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class SendMessageServiceTest {

	private KafkaTemplate<String, Task> kafkaTemplateMock;
	private SendMessageService sendMessageService;

	@BeforeEach
	void initialize() {
		kafkaTemplateMock =	Mockito.mock(KafkaTemplate.class);
		sendMessageService = new SendMessageService(kafkaTemplateMock);
	}

	@Test
	void shouldSendMessageToKafkaIfTaskIsCompleted() {
		var completedTask = Task.builder().id(1L).title("title").description("description").completed(true).build();

		sendMessageService.taskCompleted(completedTask);

		verify(kafkaTemplateMock)
				.send(eq(KafkaConstants.Topics.TASK_COMPLETED), any(String.class), eq(completedTask));
	}

	@Test
	void shouldNotSendMessageToKafkaIfTaskIsNotCompleted() {
		var incompleteTask = Task.builder().id(1L).title("title").description("description").completed(false).build();

		sendMessageService.taskCompleted(incompleteTask);

		verify(kafkaTemplateMock, never()).send(anyString(), anyString(), any(Task.class));
	}

}
