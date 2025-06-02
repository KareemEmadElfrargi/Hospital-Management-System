package com.kareem.Hospital_Management_System.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "shifts")

public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ShiftType type;

    private LocalDateTime startTime;
    private LocalDateTime  endTime;

    @ManyToOne
    @JoinColumn(name = "nurse_national_id")
    private Nurse nurse;
}
