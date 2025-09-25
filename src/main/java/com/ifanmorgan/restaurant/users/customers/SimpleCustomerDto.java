package com.ifanmorgan.restaurant.users.customers;

import com.ifanmorgan.restaurant.events.EventDto;
import com.ifanmorgan.restaurant.orders.SimpleOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class SimpleCustomerDto {
    private String firstName;
    private String lastName;
    private String houseNumber;
    private String street;
    private String postcode;
}
