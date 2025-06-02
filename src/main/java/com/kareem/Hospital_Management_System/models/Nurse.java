package com.kareem.Hospital_Management_System.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "nurse")
    private List<Shift> shifts;

    @ManyToMany
    private List<Patient> patients;

}