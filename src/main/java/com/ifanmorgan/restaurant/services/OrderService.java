package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.entities.*;
import com.ifanmorgan.restaurant.exceptions.CustomerNotFoundException;
import com.ifanmorgan.restaurant.exceptions.MenuItemNotFoundException;
import com.ifanmorgan.restaurant.exceptions.OrderAlreadyPlacedException;
import com.ifanmorgan.restaurant.exceptions.OrderNotFoundException;
import com.ifanmorgan.restaurant.mappers.OrderMapper;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.MenuItemRepository;
import com.ifanmorgan.restaurant.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderDto createRestaurantOrder(CreateRestaurantOrderRequest request) {
        var customerId = request.getCustomerId();


        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var order = new RestaurantOrder();
        order.setCustomer(customer);

        orderRepository.save(order);
        return orderMapper.toOrderDto(order);
    }

    public OrderDto createTakeoutOrder(CreateTakeoutOrderRequest request) {
        var customerId = request.getCustomerId();
        var customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var order = new TakeoutOrder();
        order.setCustomer(customer);
        order.setPickupTime(request.getPickupTime());
        orderRepository.save(order);
        return orderMapper.toOrderDto(order);
    }

    public OrderDto createDeliveryOrder(CreateDeliveryOrderRequest request) {
        var customerId = request.getCustomerId();
        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var order = new DeliveryOrder();
        order.setCustomer(customer);

        orderRepository.save(order);
        return orderMapper.toOrderDto(order);

    }

    public OrderItemDto addOrderItem(Long itemId, UUID id) {
        var order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        if (order.getOrderStatus() != OrderStatus.IN_PROGRESS) {
            throw new OrderAlreadyPlacedException();
        }

        var menuItem = menuItemRepository.findById(itemId).orElse(null);

        if (menuItem == null) {
            throw new MenuItemNotFoundException();
        }

        var orderItem = order.addItem(menuItem);

        orderRepository.save(order);

        return orderMapper.toOrderItemDto(orderItem);
    }

    public List<OrderDto> getAllOrders() {
        var orders = orderRepository.findAll();
        return orders
                .stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getOutstandingOrders() {
        var orders = orderRepository.findByOrderStatus(OrderStatus.PLACED);
        return orders
                .stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(UUID id) {
        var order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        return orderMapper.toOrderDto(order);
    }

    public void placeOrder(UUID id) {
        var order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        if (order.getOrderStatus() != OrderStatus.IN_PROGRESS) {
            throw new OrderAlreadyPlacedException();
        }

        order.setOrderStatus(OrderStatus.PLACED);

        orderRepository.save(order);
    }

    public void completeOrder(UUID id) {
        var order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        order.setOrderStatus(OrderStatus.COMPLETED);

        orderRepository.save(order);
    }

}
