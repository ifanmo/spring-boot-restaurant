package com.ifanmorgan.restaurant.reports;

import com.ifanmorgan.restaurant.users.customers.CustomerMapper;
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

    public ReportDto getReport() {
        var reportDto = new ReportDto();
        var customer = customerRepository.findMostActiveCustomer(PageRequest.of(0, 1)).get(0);
        var customerDto = new SimpleCustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getHouseNumber(),
                customer.getStreet(),
                customer.getPostcode());
        reportDto.setMostActiveCustomer(customerDto);
        var staff = staffRepository.findMostActiveStaff(PageRequest.of(0, 1)).get(0);
        var staffDto = new SimpleStaffDto(staff.getFirstName(), staff.getLastName());
        reportDto.setMostActiveStaff(staffDto);
        return reportDto;
    }
}
