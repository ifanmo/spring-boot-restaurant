package com.ifanmorgan.restaurant.users.customers;

import com.ifanmorgan.restaurant.orders.RestaurantCheckoutResponse;
import lombok.Data;

import java.util.Set;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String houseNumber;
    private String street;
    private String postcode;
    private Set<RestaurantCheckoutResponse> orders;
}
