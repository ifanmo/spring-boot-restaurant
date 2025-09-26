package com.ifanmorgan.restaurant.reports;

import com.ifanmorgan.restaurant.bookings.TimeSlotDto;
import com.ifanmorgan.restaurant.bookings.TimeSlotRepository;
import com.ifanmorgan.restaurant.menu.MenuItemRepository;
import com.ifanmorgan.restaurant.menu.MenuMapper;
import com.ifanmorgan.restaurant.users.customers.CustomerRepository;
import com.ifanmorgan.restaurant.users.customers.SimpleCustomerDto;
import com.ifanmorgan.restaurant.users.staff.SimpleStaffDto;
import com.ifanmorgan.restaurant.users.staff.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReportService {
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuMapper menuMapper;
    private final TimeSlotRepository timeSlotRepository;

    public ReportDto getReport() {
        var reportDto = new ReportDto();
        var customers = customerRepository.findMostActiveCustomer(PageRequest.of(0, 5));
        var customerResponse = customers
                .stream()
                .map(c -> new SimpleCustomerDto(
                        c.getFirstName(),
                        c.getLastName(),
                        c.getHouseNumber(),
                        c.getStreet(),
                        c.getPostcode()))
                .toList();
        reportDto.setMostActiveCustomers(customerResponse);

        var staff = staffRepository.findMostActiveStaff(PageRequest.of(0, 5));
        var staffResponse = staff.stream().map(s -> new SimpleStaffDto(s.getFirstName(), s.getLastName(), s.calculateHoursWorked())).toList();
        reportDto.setMostActiveStaff(staffResponse);

        var menuItems = menuItemRepository.findMostPopularMenuItems(PageRequest.of(0, 5));
        var menuItemResponse = menuItems.stream().map(menuMapper::toDto).toList();
        reportDto.setMostPopularItems(menuItemResponse);

        var timeSlots = timeSlotRepository.getBusiestPeriods(PageRequest.of(0, 5));
        var timeSlotResponse = timeSlots.stream().map(t -> new TimeSlotDto(t.getStartTime())).toList();
        reportDto.setBusiestPeriods(timeSlotResponse);

        return reportDto;
    }
}
