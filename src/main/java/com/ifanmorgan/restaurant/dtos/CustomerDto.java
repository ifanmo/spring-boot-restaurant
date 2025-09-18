package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String houseNumber;
    private String street;
    private String postcode;
    private Set<RestaurantOrderDto> orders;
}
