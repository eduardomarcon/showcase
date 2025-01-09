package com.showcase.api.controller.data.mapper;

import com.showcase.api.controller.data.request.TaskCreateRequest;
import com.showcase.api.controller.data.response.TaskCreateResponse;
import com.showcase.api.domain.model.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskRestMapper {

	Task toModel(TaskCreateRequest taskCreateRequest);

	TaskCreateResponse toCreateResponse(Task task);

}
