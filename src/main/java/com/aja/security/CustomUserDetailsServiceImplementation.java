package com.aja.security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aja.entity.User;
import com.aja.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsServiceImplementation(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Safe role handling
        String role = user.getRole() != null ? user.getRole().toUpperCase() : "USER";
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        // Safe active check
        boolean isActive = Boolean.TRUE.equals(user.getIsActive());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.singletonList(
                        new SimpleGrantedAuthority(role)))
                .disabled(!isActive)
                .build();
    }
}