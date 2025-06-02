package com.kareem.Hospital_Management_System.dto;

import com.kareem.Hospital_Management_System.models.ShiftType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShiftRequest {
    private String nurseNationalId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ShiftType shiftType;
}