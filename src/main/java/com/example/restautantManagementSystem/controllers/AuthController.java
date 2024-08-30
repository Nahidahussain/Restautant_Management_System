package com.example.restautantManagementSystem.controllers;

import com.example.restautantManagementSystem.dtos.AuthenticationRequest;
import com.example.restautantManagementSystem.dtos.AuthenticationResponse;
import com.example.restautantManagementSystem.dtos.SignupRequest;
import com.example.restautantManagementSystem.dtos.UsersDto;
import com.example.restautantManagementSystem.services.auth.AuthServiceImpl;
import com.example.restautantManagementSystem.services.auth.jwt.UserDetailsServiceImpl;
import com.example.restautantManagementSystem.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user/auth")
public class AuthController {


    private final AuthServiceImpl authService;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthServiceImpl authService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        UsersDto createdUserDto = authService.createUser(signupRequest);
        if(createdUserDto == null){
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }
        catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final  String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
