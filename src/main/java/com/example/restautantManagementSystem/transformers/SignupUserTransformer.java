package com.example.restautantManagementSystem.transformers;

import com.example.restautantManagementSystem.dtos.SignupRequest;
import com.example.restautantManagementSystem.dtos.UsersDto;
import com.example.restautantManagementSystem.enums.UserRole;
import com.example.restautantManagementSystem.models.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SignupUserTransformer {

    public static Users signupRequestToUsers(SignupRequest signupRequest){
        Users users = new Users();
        users.setName(signupRequest.getName());
        users.setEmail(signupRequest.getEmail());
        users.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        users.setUserRole(UserRole.CUSTOMER);
        return users;
    }
    public static UsersDto usersToUserDto(Users users){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setName(users.getName());
        usersDto.setEmail(users.getEmail());
        usersDto.setPassword(users.getPassword());
        usersDto.setUserRole(users.getUserRole());
        return usersDto;
    }

}
