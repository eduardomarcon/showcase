package com.showcase.api.domain.service;

import com.showcase.api.domain.model.User;
import com.showcase.api.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateUserService {

	private final UserRepository repository;

	public CreateUserService(UserRepository repository) {
		this.repository = repository;
	}

	public User execute(User newUser) {
		log.info("inserting USER {}", newUser);
		return repository.save(newUser);
	}

}
