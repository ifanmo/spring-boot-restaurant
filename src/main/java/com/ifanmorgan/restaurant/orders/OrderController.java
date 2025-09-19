package com.ifanmorgan.restaurant.orders;

import com.ifanmorgan.restaurant.carts.CartIsEmptyException;
import com.ifanmorgan.restaurant.carts.CartNotFoundException;
import com.ifanmorgan.restaurant.menu.MenuItemNotFoundException;
import com.ifanmorgan.restaurant.misc.ErrorDto;
import com.ifanmorgan.restaurant.users.customers.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
class OrderController {
    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(
            @Valid @RequestBody CheckoutRequest request) {

            var orderDto = orderService.checkout(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
        }


    @GetMapping
    public ResponseEntity<List<SimpleOrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedOrderDto> getOrder(@PathVariable Long id) {
        var orderDto = orderService.getOrder(id);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/outstanding")
    public ResponseEntity<List<SimpleOrderDto>> getOutstandingOrders() {
        return ResponseEntity.ok(orderService.getOutstandingOrders());
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        orderService.approve(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        orderService.complete(id);
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler({
            CustomerNotFoundException.class,
            OrderNotFoundException.class,
            MenuItemNotFoundException.class,
            CartNotFoundException.class,
    })
    public ResponseEntity<ErrorDto> handleCustomerNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler({CartNotFoundException.class, CartIsEmptyException.class, OrderAlreadyPlacedException.class})
    public ResponseEntity<ErrorDto> handleCartNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }

}
