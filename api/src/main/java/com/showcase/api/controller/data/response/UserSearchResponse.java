package com.showcase.api.controller.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSearchResponse {

	private Long id;
	private String role;
	private String username;
	private String email;
	private boolean active;
	private String createdAt;

}
