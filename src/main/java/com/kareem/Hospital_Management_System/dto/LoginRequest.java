package com.kareem.Hospital_Management_System.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String nationalId;
    private String password;
}