package com.ifanmorgan.restaurant.users.staff;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    ShiftDto toDto(Shift shift);
}
