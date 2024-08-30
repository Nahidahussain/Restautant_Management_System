package com.example.restautantManagementSystem.services.auth;

import com.example.restautantManagementSystem.dtos.SignupRequest;
import com.example.restautantManagementSystem.dtos.UsersDto;
import com.example.restautantManagementSystem.models.Users;
import com.example.restautantManagementSystem.repositories.UserRepository;
import com.example.restautantManagementSystem.transformers.SignupUserTransformer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersDto createUser(SignupRequest signupRequest) {
        Users users = SignupUserTransformer.signupRequestToUsers(signupRequest);
        Users createdUser = userRepository.save(users);
        return SignupUserTransformer.usersToUserDto(createdUser);
    }
}
