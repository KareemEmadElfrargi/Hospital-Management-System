package com.kareem.Hospital_Management_System.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    NURSE;

    @Override
    public String getAuthority() {
        return name();
    }
}