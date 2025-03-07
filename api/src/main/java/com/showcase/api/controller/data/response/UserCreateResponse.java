package com.showcase.api.controller.data.response;

import com.showcase.api.domain.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateResponse {

	private Long id;
	private UserRole role;
	private String username;
	private String email;

}
