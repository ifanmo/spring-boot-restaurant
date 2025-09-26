package com.ifanmorgan.restaurant.reports;

import com.ifanmorgan.restaurant.bookings.TimeSlot;
import com.ifanmorgan.restaurant.bookings.TimeSlotDto;
import com.ifanmorgan.restaurant.menu.MenuItem;
import com.ifanmorgan.restaurant.menu.MenuItemDto;
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
