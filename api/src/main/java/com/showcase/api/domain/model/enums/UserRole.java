package com.showcase.api.domain.model.enums;

import java.util.Arrays;

public enum UserRole {

	ADMIN(0, "Admin"),
	USER(1, "User");

	private final int id;
	private final String description;

	UserRole(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static UserRole fromId(int id) {
		return Arrays.stream(UserRole.values())
				.filter(userRole -> userRole.getId() == id)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
	}

}
