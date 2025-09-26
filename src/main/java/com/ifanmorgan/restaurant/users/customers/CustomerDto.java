package com.ifanmorgan.restaurant.users.customers;

import com.ifanmorgan.restaurant.events.EventDto;
import com.ifanmorgan.restaurant.orders.dtos.SimpleOrderDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String houseNumber;
    private String street;
    private String postcode;
    private List<SimpleOrderDto> orders;
    private Set<EventDto> events;
}
