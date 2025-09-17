package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String streetNumber;
    private String street;
    private String postcode;
}
