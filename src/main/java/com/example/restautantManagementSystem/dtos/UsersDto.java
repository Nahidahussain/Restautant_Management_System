package com.example.restautantManagementSystem.dtos;

import com.example.restautantManagementSystem.enums.UserRole;
import lombok.Data;

@Data
public class UsersDto {
    long id;

    String name;
    String email;
    String password;
    UserRole userRole;
}
