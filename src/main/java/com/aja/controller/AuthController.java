package com.aja.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aja.dto.AuthResponseDto;
import com.aja.dto.LoginRequestDto;
import com.aja.security.JwtUtil;

@RestController // REQUIRED: Tells Spring this is an API controller
@RequestMapping("/auth") // REQUIRED: Matches your .requestMatchers("/auth/**").permitAll()
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // REQUIRED: Constructor injection ensures these are not null
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/login") // REQUIRED: Defines the endpoint URL as /auth/login
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        // 1. Authenticate the user
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. Generate Token
        String token = jwtUtil.generateToken(request.getEmail());

        // 3. Return Response
        return ResponseEntity.ok(new AuthResponseDto(token, "Bearer"));
    }
}