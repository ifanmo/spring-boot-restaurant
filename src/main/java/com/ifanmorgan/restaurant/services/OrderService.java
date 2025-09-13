package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.entities.DeliveryOrder;
import com.ifanmorgan.restaurant.entities.RestaurantOrder;
import com.ifanmorgan.restaurant.entities.TakeoutOrder;
import com.ifanmorgan.restaurant.exceptions.CustomerNotFoundException;
import com.ifanmorgan.restaurant.mappers.OrderMapper;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.OrderRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository restaurantOrderRepository;
    private final OrderRepository takeoutOrderRepository;
    private final OrderRepository deliveryOrderRepository;
    private final OrderMapper orderMapper;
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
        return orderMapper.toRestaurantDto(order);
    }

    public TakeoutOrderDto createTakeoutOrder(CreateTakeoutOrderRequest request) {
        var customerId = request.getCustomerId();
        var customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var order = new TakeoutOrder();
        order.setCustomer(customer);
        order.setPickupTime(request.getPickupTime());
        takeoutOrderRepository.save(order);
        return orderMapper.toTakeoutDto(order);
    }

    public DeliveryOrderDto createDeliveryOrder(CreateDeliveryOrderRequest request) {
        var customerId = request.getCustomerId();
        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var order = new DeliveryOrder();
        order.setCustomer(customer);

        deliveryOrderRepository.save(order);
        return orderMapper.toDeliveryOrderDto(order);

    }
}
