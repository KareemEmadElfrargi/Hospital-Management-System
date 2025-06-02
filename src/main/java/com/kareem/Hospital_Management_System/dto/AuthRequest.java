package com.kareem.Hospital_Management_System.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String name;
    private String password;
    private String nationalId;
}
