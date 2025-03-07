package com.showcase.api.controller;

import com.showcase.api.controller.data.mapper.UserRestMapper;
import com.showcase.api.controller.data.request.UserCreateRequest;
import com.showcase.api.controller.data.response.CommonResponse;
import com.showcase.api.controller.data.response.UserCreateResponse;
import com.showcase.api.domain.service.CreateUserService;
import com.showcase.api.domain.service.GetOneUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Users", description = "User handling services")
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserRestMapper userMapper = Mappers.getMapper(UserRestMapper.class);
	private final CreateUserService createUserService;
	private final GetOneUserService getOneUserService;

	public UserController(CreateUserService createUserService, GetOneUserService getOneUserService) {
		this.createUserService = createUserService;
		this.getOneUserService = getOneUserService;
	}

	@PostMapping
	@Operation(summary = "Create a new user", description = "Creates a new user")
	@ApiResponse(responseCode = "201", description = "User created successfully")
	@ApiResponse(responseCode = "400", description = "Invalid user data")
	public ResponseEntity<UserCreateResponse> createUser(
			@RequestBody @Valid final UserCreateRequest userCreateRequest) {
		log.info("inserting USER {}", userCreateRequest);
		var newUser = userMapper.toModel(userCreateRequest);
		var createdUser = createUserService.execute(newUser);
		var userCreateResponse = userMapper.toCreateResponse(createdUser);

		return new ResponseEntity<>(userCreateResponse, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	@Operation(summary = "Get a user", description = "Returns the user with the given ID.")
	@Parameter(name = "id", description = "The ID of the user", required = true,
			   schema = @Schema(type = "integer", format = "int64"))
	@ApiResponse(responseCode = "200", description = "Task found")
	@ApiResponse(responseCode = "400", description = "Task not found", content = @Content)
	public ResponseEntity<CommonResponse> getTask(@PathVariable final Long id) {
		var foundUser = getOneUserService.byId(id);
		var userSearchResponse = userMapper.toSearchResponse(foundUser);

		return ResponseEntity.ok(new CommonResponse<>(userSearchResponse));
	}

}
