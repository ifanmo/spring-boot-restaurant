package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.CreateStaffProfileRequest;
import com.ifanmorgan.restaurant.dtos.StaffDto;
import com.ifanmorgan.restaurant.entities.users.Staff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    Staff toEntity(CreateStaffProfileRequest request);

    StaffDto toDto(Staff staff);
}
