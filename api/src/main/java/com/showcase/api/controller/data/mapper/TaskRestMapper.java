package com.showcase.api.controller.data.mapper;

import com.showcase.api.controller.data.request.TaskCreateRequest;
import com.showcase.api.controller.data.response.TaskCreateResponse;
import com.showcase.api.controller.data.response.TaskSearchResponse;
import com.showcase.api.domain.model.Task;
import com.showcase.api.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TaskRestMapper {

	@Mapping(target = "user", source = "userId", qualifiedByName = "userIdToModel")
	Task toModel(TaskCreateRequest taskCreateRequest);

	TaskCreateResponse toCreateResponse(Task task);

	@Mapping(target = "userId", source = "user.id")
	TaskSearchResponse toSearchResponse(Task task);

	@Named("userIdToModel")
	static User userIdToModel(Long userId) {
		return User.builder().id(userId).build();
	}

}
