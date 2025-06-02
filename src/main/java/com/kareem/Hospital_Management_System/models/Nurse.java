package com.kareem.Hospital_Management_System.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "nurse")
    private List<Shift> shifts;

    @ManyToMany
    private List<Patient> patients;
}