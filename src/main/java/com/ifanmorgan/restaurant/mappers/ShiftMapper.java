package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.ShiftDto;
import com.ifanmorgan.restaurant.entities.users.Shift;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    ShiftDto toDto(Shift shift);
}
