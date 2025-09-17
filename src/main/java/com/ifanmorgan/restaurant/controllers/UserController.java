package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.RegisterUserRequest;
import com.ifanmorgan.restaurant.dtos.UserDto;
import com.ifanmorgan.restaurant.mappers.UserMapper;
import com.ifanmorgan.restaurant.repositories.UserRepository;
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
public class UserController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final UserRepository userRepository;

    @PostMapping
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
