package com.ifanmorgan.restaurant.users;

import com.ifanmorgan.restaurant.users.customers.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "A user can register an account with role 'CUSTOMER', 'MANAGER', 'CHEF', 'WAITER' or 'DELIVERY_DRIVER'")
    public ResponseEntity<UserDto> registerUser(
            @Valid @RequestBody RegisterUserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        var user = mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(user));
    }
}
