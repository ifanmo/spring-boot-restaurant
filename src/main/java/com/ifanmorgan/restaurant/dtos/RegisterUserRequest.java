package com.ifanmorgan.restaurant.dtos;

import com.ifanmorgan.restaurant.entities.users.Role;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
    private Role role;
}
