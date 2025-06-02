package com.kareem.Hospital_Management_System.controller;

import com.kareem.Hospital_Management_System.dto.AuthRequest;
import com.kareem.Hospital_Management_System.dto.LoginRequest;
import com.kareem.Hospital_Management_System.models.Role;
import com.kareem.Hospital_Management_System.models.User;
import com.kareem.Hospital_Management_System.repository.UserRepository;
import com.kareem.Hospital_Management_System.service.AuthenticationService;
import com.kareem.Hospital_Management_System.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/create-nurse")
    public ResponseEntity<String> createNurse(@RequestBody AuthRequest request, Authentication authentication) {
        try {
            String message = authenticationService.createNurse(request, authentication);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
