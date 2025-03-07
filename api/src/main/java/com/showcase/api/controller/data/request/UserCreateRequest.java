package com.showcase.api.controller.data.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateRequest {

	@NotEmpty(message = "username may not be empty")
	private String username;
	@NotEmpty(message = "password may not be empty")
	private String password;
	@NotEmpty(message = "email may not be empty")
	private String email;
	private Boolean active;
	private String role;

}
