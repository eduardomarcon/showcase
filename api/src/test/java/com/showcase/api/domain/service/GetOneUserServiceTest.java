package com.showcase.api.domain.service;

import com.showcase.api.domain.model.User;
import com.showcase.api.domain.model.enums.UserRole;
import com.showcase.api.domain.repository.UserRepository;
import com.showcase.api.support.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetOneUserServiceTest {

	private UserRepository userRepository;
	private GetOneUserService getOneUserService;

	@BeforeEach
	void initialize() {
		userRepository = mock(UserRepository.class);
		getOneUserService = new GetOneUserService(userRepository);
	}

	@Test
	void shouldReturnUserWhenUserExists() {
		var userId = 1L;
		var expectedUser = User.builder().id(userId).username("testUser").password("securePassword")
				.email("testuser@example.com").role(UserRole.USER).active(true).build();

		when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

		var actualUser = getOneUserService.byId(userId);

		assertEquals(expectedUser, actualUser);
	}

	@Test
	void shouldThrowEntityNotFoundExceptionWhenUserDoesNotExist() {
		var userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> getOneUserService.byId(userId));
	}

}
