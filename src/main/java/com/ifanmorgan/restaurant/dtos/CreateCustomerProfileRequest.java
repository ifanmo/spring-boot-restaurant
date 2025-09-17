package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCustomerProfileRequest {
    @NotNull(message = "first name is required")
    private String firstName;
    @NotNull(message = "last name is required")
    private String lastName;
    @NotNull(message = "street number is required")
    private String streetNumber;
    @NotNull(message = "street name is required")
    private String street;
    @NotNull(message = "postcode is required")
    private String postcode;

}
