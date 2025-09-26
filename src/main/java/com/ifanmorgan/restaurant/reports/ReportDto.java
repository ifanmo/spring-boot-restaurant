package com.ifanmorgan.restaurant.reports;

import com.ifanmorgan.restaurant.bookings.dtos.TimeSlotDto;
import com.ifanmorgan.restaurant.menu.dtos.MenuItemDto;
import com.ifanmorgan.restaurant.users.customers.SimpleCustomerDto;
import com.ifanmorgan.restaurant.users.staff.SimpleStaffDto;
import lombok.Data;

import java.util.List;

@Data
public class ReportDto {
    private List<SimpleCustomerDto> mostActiveCustomers;
    private List<SimpleStaffDto> mostActiveStaff;
    private List<MenuItemDto> mostPopularItems;
    private List<TimeSlotDto> busiestPeriods;
}
