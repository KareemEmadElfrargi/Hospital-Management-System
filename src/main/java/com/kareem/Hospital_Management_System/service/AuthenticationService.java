package com.kareem.Hospital_Management_System.service;

import com.kareem.Hospital_Management_System.dto.AuthRequest;
import com.kareem.Hospital_Management_System.dto.LoginRequest;
import com.kareem.Hospital_Management_System.models.Nurse;
import com.kareem.Hospital_Management_System.models.Role;
import com.kareem.Hospital_Management_System.models.User;
import com.kareem.Hospital_Management_System.repository.NurseRepository;
import com.kareem.Hospital_Management_System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final NurseRepository nurseRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(LoginRequest request) {
        User user = userRepository.findByNationalId(request.getNationalId())
                .orElseThrow(() -> new RuntimeException("User Not Found in DB"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
        return jwtService.generateToken(user.getName());
    }

    public String createNurse(AuthRequest request, Authentication authentication) {
        User currentUser = userRepository.findByName(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!Role.ADMIN.equals(currentUser.getRole())) {
            throw new RuntimeException("Only admins can create nurses Account");
        }

        if (userRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.findByNationalId(request.getNationalId()).isPresent()) {
            throw new RuntimeException("National ID already exists");
        }

        User nurseUser = User.builder()
                .name(request.getName())
                .nationalId(request.getNationalId())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.NURSE)
                .build();

        Nurse nurseInfo = Nurse.builder()
                .nationalId(request.getNationalId())
                .name(request.getName())
                .phone("+20 0000000000").build();

        nurseRepository.save(nurseInfo);
        userRepository.save(nurseUser);

        return "Nurse created successfully";
    }
}
