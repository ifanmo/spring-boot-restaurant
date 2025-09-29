package com.ifanmorgan.restaurant.orders;

import com.ifanmorgan.restaurant.carts.Cart;
import com.ifanmorgan.restaurant.carts.exceptions.CartIsEmptyException;
import com.ifanmorgan.restaurant.carts.exceptions.CartNotFoundException;
import com.ifanmorgan.restaurant.carts.CartRepository;
import com.ifanmorgan.restaurant.carts.CartService;
import com.ifanmorgan.restaurant.auth.AuthService;
import com.ifanmorgan.restaurant.orders.dtos.*;
import com.ifanmorgan.restaurant.orders.exceptions.OrderNotFoundException;
import com.ifanmorgan.restaurant.users.customers.Customer;
import com.ifanmorgan.restaurant.users.customers.CustomerNotFoundException;
import com.ifanmorgan.restaurant.users.customers.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final AuthService authService;
    private final CartService cartService;

    public List<SimpleOrderDto> getAllOrders() {
        var orders = orderRepository.findAll();
        return orders
                .stream().map(orderMapper::toSimpleOrderDto)
                .toList();
    }

    public List<SimpleOrderDto> getOutstandingOrders() {
        var orders = orderRepository.findByOrderStatus(OrderStatus.APPROVED);
        return orders
                .stream()
                .map(orderMapper::toSimpleOrderDto)
                .toList();
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


    public OrderDto createRestaurantOrder(UUID cartId) {
        var cart = getValidatedCart(cartId);
        var customer = getCurrentCustomer();

        var order = RestaurantOrder.fromCart(cart, customer);
        order = (RestaurantOrder) processOrder(order, cartId);
        return orderMapper.toRestaurantOrderDto(order);
    }

    public OrderDto createTakeoutOrder(UUID cartId, LocalTime pickupTime) {
        var cart = getValidatedCart(cartId);
        var customer = getCurrentCustomer();

        var order = TakeoutOrder.fromCart(cart, customer, pickupTime);
        order = (TakeoutOrder) processOrder(order, cartId);
        return orderMapper.toTakeoutOrderDto(order);
    }

    public OrderDto createDeliveryOrder(UUID cartId, LocalTime deliveryTime) {
        var cart = getValidatedCart(cartId);
        var customer = getCurrentCustomer();

        var order = DeliveryOrder.fromCart(cart, customer, deliveryTime);
        order = (DeliveryOrder)processOrder(order, cartId);
        return orderMapper.toDeliveryOrderDto(order);
    }

    private Cart getValidatedCart(UUID cartId) {
        var cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        if (cart.isEmpty()) {
            throw new CartIsEmptyException();
        }
        return cart;
    }

    private Customer getCurrentCustomer() {
        var user = authService.getCurrentUser();
        return customerRepository.findById(user.getId()).orElseThrow(CustomerNotFoundException::new);
    }

    private Order processOrder(Order order, UUID cartId) {
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);
        cartService.clearCart(cartId);
        return order;
    }
}


