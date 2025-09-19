package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.entities.*;
import com.ifanmorgan.restaurant.entities.users.Role;
import com.ifanmorgan.restaurant.exceptions.*;
import com.ifanmorgan.restaurant.mappers.OrderMapper;
import com.ifanmorgan.restaurant.repositories.*;
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
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final AuthService authService;
    private final CartService cartService;
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;

    public List<SimpleOrderDto> getAllOrders() {
        var orders = orderRepository.findAll();
        return orders
                .stream().map(orderMapper::toSimpleOrderDto)
                .collect(Collectors.toList());
    }

    public List<SimpleOrderDto> getOutstandingOrders() {
        var orders = orderRepository.findByOrderStatus(OrderStatus.APPROVED);
        return orders
                .stream()
                .map(orderMapper::toSimpleOrderDto)
                .collect(Collectors.toList());
    }

    public DetailedOrderDto getOrder(Long id) {
        var order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        return orderMapper.toDetailedOrderDto(order);
    }



    public void approve(Long id) {
        var order = orderRepository.findByIdAndOrderStatus(id, OrderStatus.PENDING).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        order.setOrderStatus(OrderStatus.APPROVED);

        orderRepository.save(order);
    }

    public void complete(Long id) {
        var order = orderRepository.findByIdAndOrderStatus(id, OrderStatus.APPROVED).orElse(null);
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

        if (cart.isEmpty()) {
            throw new CartIsEmptyException();
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
