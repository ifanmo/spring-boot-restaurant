package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.CreateStaffProfileRequest;
import com.ifanmorgan.restaurant.dtos.StaffDto;
import com.ifanmorgan.restaurant.entities.users.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    Staff toEntity(CreateStaffProfileRequest request);

    @Mapping(target = "hoursWorked", expression = "java(staff.calculateHoursWorked())")
    StaffDto toDto(Staff staff);
}
