package com.ifanmorgan.restaurant.users.staff;

import lombok.Data;

import java.util.List;

@Data
public class StaffDto {
    private String firstName;
    private String lastName;
    private List<ShiftDto> shifts;
    private int hoursWorked;
}
