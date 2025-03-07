package com.showcase.api.controller.data.mapper;

import com.showcase.api.controller.data.request.UserCreateRequest;
import com.showcase.api.controller.data.response.UserCreateResponse;
import com.showcase.api.controller.data.response.UserSearchResponse;
import com.showcase.api.domain.model.User;
import com.showcase.api.domain.model.enums.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface UserRestMapper {

	@Mapping(target = "role", source = "role", qualifiedByName = "StringToRole")
	@Mapping(target = "active", source = "active", qualifiedByName = "DefaultActive")
	User toModel(UserCreateRequest userCreateRequest);

	@Mapping(target = "role", source = "role", qualifiedByName = "RoleToString")
	UserCreateResponse toCreateResponse(User user);

	@Mapping(target = "role", source = "role", qualifiedByName = "RoleToString")
	UserSearchResponse toSearchResponse(User user);

	@Named("DefaultActive")
	static boolean defaultActive(Boolean active) {
		if (active == null) return true;
		return active;
	}

	@Named("StringToRole")
	static UserRole stringToRole(String role) {
		if (role == null || role.isEmpty()) return UserRole.USER;

		return UserRole.valueOf(role.toUpperCase());
	}

	@Named("RoleToString")
	static String roleToString(UserRole userRole) {
		return userRole.name();
	}

}
