package com.showcase.api.domain.consumer;


import com.showcase.api.domain.model.Task;
import com.showcase.api.support.util.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompletedTaskConsumer {

	@KafkaListener(topics = KafkaConstants.Topics.TASK_COMPLETED, groupId = "task-completed")
	public void consume(Task task) {
		log.info("consuming TASK: {}", task);
	}

}
