package com.example.restautantManagementSystem.services.auth.jwt;

import com.example.restautantManagementSystem.models.Users;
import com.example.restautantManagementSystem.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //write logic to get user from db
        Optional<Users> optionalUsers = userRepository.findFirstByEmail(email);
        if(optionalUsers.isEmpty())
            throw new UsernameNotFoundException("User not found",null);
        return new org.springframework.security.core.userdetails.User(optionalUsers.get().getEmail(),optionalUsers.get().getPassword(),new ArrayList<>());
    }
}
