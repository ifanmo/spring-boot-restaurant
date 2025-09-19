package com.ifanmorgan.restaurant.users;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
    private Role role;
}
