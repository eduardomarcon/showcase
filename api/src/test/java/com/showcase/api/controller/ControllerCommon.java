package com.showcase.api.controller;

import com.showcase.api.config.TodoPostgresqlContainer;
import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ControllerCommon {

	@ClassRule
	public static PostgreSQLContainer<TodoPostgresqlContainer> pgSQLContainer = TodoPostgresqlContainer.getInstance();

}
