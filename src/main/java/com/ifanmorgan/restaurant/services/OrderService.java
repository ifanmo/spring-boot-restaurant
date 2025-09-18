package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.entities.*;
import com.ifanmorgan.restaurant.exceptions.*;
import com.ifanmorgan.restaurant.mappers.OrderMapper;
import com.ifanmorgan.restaurant.repositories.CartRepository;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final CartService cartService;

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

    public OrderDto getOrder(Long id) {
        var order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        return orderMapper.toOrderDto(order);
    }



    public void completeOrder(Long id) {
        var order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        order.setOrderStatus(OrderStatus.COMPLETED);

        orderRepository.save(order);
    }


    public OrderDto checkout(CheckoutRequest request) {
        var cart = cartRepository.findById(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        if (cart.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No cart items found");
        }

        var user = authService.getCurrentUser();
        var customer = customerRepository.findById(user.getId()).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        switch (request.getOrderType()) {
            case RESTAURANT -> {
                var order = RestaurantOrder.fromCart(cart, customer);
                orderRepository.save(order);
                cartService.clearCart(cart.getId());
                return orderMapper.toRestaurantOrderDto(order);
            }
            case TAKEAWAY -> {
                var order = TakeoutOrder.fromCart(cart, customer, request.getPickupTime());
                orderRepository.save(order);
                cartService.clearCart(cart.getId());
                return orderMapper.toTakeoutOrderDto(order);
            }
            case DELIVERY -> {
                var order = DeliveryOrder.fromCart(cart, customer, request.getDeliveryTime());
                orderRepository.save(order);
                cartService.clearCart(cart.getId());
                return orderMapper.toDeliveryOrderDto(order);
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order type");
        }
    }





}
