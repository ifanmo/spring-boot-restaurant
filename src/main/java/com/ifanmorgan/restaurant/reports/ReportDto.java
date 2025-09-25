package com.ifanmorgan.restaurant.reports;

import com.ifanmorgan.restaurant.bookings.TimeSlot;
import com.ifanmorgan.restaurant.menu.MenuItem;
import com.ifanmorgan.restaurant.users.customers.SimpleCustomerDto;
import com.ifanmorgan.restaurant.users.staff.SimpleStaffDto;
import lombok.Data;

@Data
public class ReportDto {
    private SimpleCustomerDto mostActiveCustomer;
    private SimpleStaffDto mostActiveStaff;
    private MenuItem mostPopularItem;
    private TimeSlot mostPopularTimeSlot;
}
