package com.showcase.api.controller.data.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskCreateRequest {

	@NotEmpty(message = "title may not be empty")
	private String title;
	private String description;

}
