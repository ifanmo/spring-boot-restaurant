package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.RegisterUserRequest;
import com.ifanmorgan.restaurant.dtos.UserDto;
import com.ifanmorgan.restaurant.entities.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(RegisterUserRequest request);

    UserDto toDto(User user);
}
