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
@Table(name = "nurses")
public class Nurse {

    @Id
    @Column(name = "national_id", unique = true)
    private String nationalId;

    private String name;
    private String phone;

    @OneToOne
    @JoinColumn(name = "national_id", referencedColumnName = "national_id")
    private User user;

    @OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL)
    private List<Shift> shifts;



}