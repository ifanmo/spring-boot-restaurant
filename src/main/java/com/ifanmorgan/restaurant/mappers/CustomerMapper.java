package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.CreateCustomerProfileRequest;
import com.ifanmorgan.restaurant.dtos.CustomerDto;
import com.ifanmorgan.restaurant.entities.users.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CreateCustomerProfileRequest request);

    CustomerDto toDto(Customer customer);
}
