package com.showcase.api.domain.service;

import com.showcase.api.domain.model.User;
import com.showcase.api.domain.model.enums.UserRole;
import com.showcase.api.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateUserServiceTest {

	private UserRepository userRepository;
	private CreateUserService createUserService;

	@BeforeEach
	void initialize() {
		userRepository = mock(UserRepository.class);
		createUserService = new CreateUserService(userRepository);
	}

	@Test
	void shouldCreateAnUser() {
		var newUser = User.builder().username("testUser").password("securePassword").email("testuser@example.com")
				.role(UserRole.USER).active(true).build();

		var savedUser = User.builder().id(1L).username("testUser").password("securePassword")
				.email("testuser@example.com").role(UserRole.USER).active(true).build();

		when(userRepository.save(any(User.class))).thenReturn(savedUser);

		var createdUser = createUserService.execute(newUser);

		assertNotNull(createdUser);
	}

	@Test
	void shouldFailToCreateUserWhenUserIsNull() {
		when(userRepository.save(null)).thenThrow(new IllegalArgumentException("User must not be null"));

		assertThrows(IllegalArgumentException.class, () -> createUserService.execute(null));
	}

}
