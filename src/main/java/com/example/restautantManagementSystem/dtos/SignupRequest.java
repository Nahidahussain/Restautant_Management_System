package com.example.restautantManagementSystem.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SignupRequest {

    String name;
    String email;
    String password;
}
