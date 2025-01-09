package com.showcase.api.controller.data.mapper;

import com.showcase.api.controller.data.request.TodoCreateRequest;
import com.showcase.api.controller.data.response.TodoCreateResponse;
import com.showcase.api.domain.model.Todo;
import org.mapstruct.Mapper;

@Mapper
public interface TodoRestMapper {

	Todo toModel(TodoCreateRequest todoCreateRequest);

	TodoCreateResponse toCreateResponse(Todo todo);

}
