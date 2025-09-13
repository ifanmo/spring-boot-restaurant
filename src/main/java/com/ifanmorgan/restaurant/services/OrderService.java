package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.CreateRestaurantOrderRequest;
import com.ifanmorgan.restaurant.dtos.RestaurantOrderDto;
import com.ifanmorgan.restaurant.entities.OrderStatus;
import com.ifanmorgan.restaurant.entities.RestaurantOrder;
import com.ifanmorgan.restaurant.exceptions.CustomerNotFoundException;
import com.ifanmorgan.restaurant.mappers.RestaurantOrderMapper;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.OrderRepository;
import com.ifanmorgan.restaurant.repositories.RestaurantOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private final RestaurantOrderRepository restaurantOrderRepository;
    private final RestaurantOrderMapper restaurantOrderMapper;
    private final CustomerRepository customerRepository;

    public RestaurantOrderDto createRestaurantOrder(CreateRestaurantOrderRequest request) {
        var customerId = request.getCustomerId();


        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var order = new RestaurantOrder();
        order.setCustomer(customer);

        restaurantOrderRepository.save(order);
        return restaurantOrderMapper.toDto(order);
    }
}
