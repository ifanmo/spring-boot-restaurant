package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.UserDto;
import com.ifanmorgan.restaurant.entities.User;
import com.ifanmorgan.restaurant.mappers.UserMapper;
import com.ifanmorgan.restaurant.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort)
    {
        if (!Set.of("role", "email").contains(sort)) {
            sort = "email";
        }
        var users = userRepository.findAll(Sort.by(sort).descending());
        return users
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        var userDto = userMapper.toDto(user);

        return ResponseEntity.ok(userDto);
    }
}
