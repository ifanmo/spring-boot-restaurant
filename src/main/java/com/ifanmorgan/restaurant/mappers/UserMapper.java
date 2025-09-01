package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.UserDto;
import com.ifanmorgan.restaurant.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
}
