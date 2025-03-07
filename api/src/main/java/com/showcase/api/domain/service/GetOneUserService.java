package com.showcase.api.domain.service;

import com.showcase.api.domain.model.User;
import com.showcase.api.domain.repository.UserRepository;
import com.showcase.api.support.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetOneUserService {

	private final UserRepository repository;

	public GetOneUserService(UserRepository repository) {
		this.repository = repository;
	}

	@Cacheable(value = "users", key = "#id")
	public User byId(Long id) {
		log.info("getting USER {}", id);

		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}

}
