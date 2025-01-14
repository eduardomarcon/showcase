package com.showcase.api.controller.data.response;

import java.time.LocalDateTime;

public record CommonResponse<T>(T data, String message, LocalDateTime timestamp) {

	public CommonResponse(T data) {
		this(data, null, LocalDateTime.now());
	}

}
