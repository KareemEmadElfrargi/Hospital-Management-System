package com.kareem.Hospital_Management_System.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    private String nationalId;
}
