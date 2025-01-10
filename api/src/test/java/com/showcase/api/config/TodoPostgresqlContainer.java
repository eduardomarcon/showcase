package com.showcase.api.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class TodoPostgresqlContainer extends PostgreSQLContainer<TodoPostgresqlContainer> {

	private static final String IMAGE_VERSION = "postgres:15-alpine";
	private static TodoPostgresqlContainer container;

	private TodoPostgresqlContainer() {
		super(IMAGE_VERSION);
	}

	public static TodoPostgresqlContainer getInstance() {
		if (container == null) {
			container = new TodoPostgresqlContainer();
		}

		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty("DB_URL", container.getJdbcUrl());
		System.setProperty("DB_USERNAME", container.getUsername());
		System.setProperty("DB_PASSWORD", container.getPassword());
	}

}
