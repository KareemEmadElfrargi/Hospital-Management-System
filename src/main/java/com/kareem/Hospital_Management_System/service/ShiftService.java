package com.kareem.Hospital_Management_System.service;

import com.kareem.Hospital_Management_System.dto.ShiftRequest;
import com.kareem.Hospital_Management_System.models.Nurse;
import com.kareem.Hospital_Management_System.models.Shift;
import com.kareem.Hospital_Management_System.models.ShiftType;
import com.kareem.Hospital_Management_System.repository.NurseRepository;
import com.kareem.Hospital_Management_System.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final NurseRepository nurseRepository;
    private final ShiftRepository shiftRepository;

    public String assignShiftToNurse(String nurseNationalId, LocalDateTime startDate, LocalDateTime  endDate, ShiftType shiftType) {
        Nurse nurse = nurseRepository.findByUser_NationalId(nurseNationalId)
        .orElseThrow(() -> new RuntimeException("Nurse not found"));

        Shift shift = Shift.builder()
                .nurse(nurse)
                .startTime(startDate)
                .endTime(endDate)
                .type(shiftType)
                .build();

        shiftRepository.save(shift);
        return "Shift assigned successfully";
    }

    public String updateShift(Long shiftId, ShiftRequest shiftRequest) {

        Shift existingShift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Shift not found"));

        existingShift.setStartTime(shiftRequest.getStartTime());
        existingShift.setEndTime(shiftRequest.getEndTime());
        existingShift.setType(shiftRequest.getShiftType());

        shiftRepository.save(existingShift);

        return "Shift updated successfully";
    }

    public List<Shift> getShiftsByNurseNationalId(String nationalId) {
        return shiftRepository.findByNurse_NationalId(nationalId);
    }

}
