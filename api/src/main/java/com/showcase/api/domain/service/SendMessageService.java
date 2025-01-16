package com.showcase.api.domain.service;

import com.showcase.api.domain.model.Task;
import com.showcase.api.support.util.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class SendMessageService {

	private final KafkaTemplate<String, Task> kafkaTemplate;


	public SendMessageService(KafkaTemplate<String, Task> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void taskCompleted(Task task) {
		if (!task.isCompleted())
			return;

		kafkaTemplate.send(KafkaConstants.Topics.TASK_COMPLETED, UUID.randomUUID().toString(), task);
	}

}
