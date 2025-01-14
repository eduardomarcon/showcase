package com.showcase.api.controller.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskSearchResponse {

	private Long id;
	private String title;
	private String description;
	private boolean completed;
	private String createdAt;

}
