package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.entities.*;
import com.ifanmorgan.restaurant.exceptions.*;
import com.ifanmorgan.restaurant.mappers.OrderMapper;
import com.ifanmorgan.restaurant.repositories.CartRepository;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderFactory orderFactory;
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

    @Transactional
    public SimpleOrderDto checkout(UUID cartId, OrderType orderType) {
        var cart = cartRepository.findById(cartId).orElse(null);
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

        var order = orderFactory.create(orderType, cart, customer);

        orderRepository.save(order);

        cartService.clearCart(cart.getId());

        return orderMapper.toSimpleOrderDto(order);
    }


}
