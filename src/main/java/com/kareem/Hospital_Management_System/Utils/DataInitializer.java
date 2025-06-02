package com.kareem.Hospital_Management_System.Utils;

import com.kareem.Hospital_Management_System.models.Role;
import com.kareem.Hospital_Management_System.models.User;
import com.kareem.Hospital_Management_System.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByName("admin").isEmpty()) {
            User admin = User.builder()
                    .name("admin")
                    .nationalId("38360329")
                    .password(passwordEncoder.encode("38360329"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("Admin user created");
        }
    }
}
