package com.showcase.api.controller;

import com.showcase.api.controller.data.request.UserCreateRequest;
import com.showcase.api.domain.model.User;
import com.showcase.api.domain.model.enums.UserRole;
import com.showcase.api.domain.service.CreateUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerITTest extends ControllerCommonIT {

	@Autowired
	private CreateUserService createUserService;

	@Test
	void shouldInsertAUser() throws Exception {
		var userCreateRequest = new UserCreateRequest("testUser", "securePassword", "testuser@example.com",
													  Boolean.TRUE, "USER");

		mockMvc
				.perform(post("/users")
								 .contentType(MediaType.APPLICATION_JSON)
								 .content(asJsonString(userCreateRequest)))
				.andExpect(status().isCreated());
	}

	@Test
	void shouldReturnUserWhenIdExists() throws Exception {
		var createdUser = insertANewUser();

		mockMvc.perform(get("/users/{id}", createdUser.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.id").value(createdUser.getId()))
				.andExpect(jsonPath("$.data.username").value(createdUser.getUsername()));
	}

	@Test
	void shouldReturnBadRequestWhenUserNotFound() throws Exception {
		var userId = 999L;

		mockMvc.perform(get("/users/{id}", userId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.message").value("User not found"));
	}

	private User insertANewUser() {
		var newUser = new User();
		newUser.setUsername("testUser");
		newUser.setPassword("securePassword");
		newUser.setEmail("testuser@example.com");
		newUser.setActive(true);
		newUser.setRole(UserRole.USER);

		return createUserService.execute(newUser);
	}

}
