package com.example.restautantManagementSystem.services.auth;

import com.example.restautantManagementSystem.dtos.SignupRequest;
import com.example.restautantManagementSystem.dtos.UsersDto;

public interface AuthService {

    UsersDto createUser(SignupRequest signupRequest);
}
