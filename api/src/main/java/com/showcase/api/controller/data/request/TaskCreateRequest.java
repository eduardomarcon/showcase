package com.showcase.api.controller.data.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskCreateRequest {

	@NotEmpty(message = "title may not be empty")
	private String title;
	@NotEmpty(message = "description may not be empty")
	private String description;
	@Positive(message = "user must be valid")
	private Long userId;

}
