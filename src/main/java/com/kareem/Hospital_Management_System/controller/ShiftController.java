package com.kareem.Hospital_Management_System.controller;

import com.kareem.Hospital_Management_System.dto.ShiftRequest;
import com.kareem.Hospital_Management_System.models.Shift;
import com.kareem.Hospital_Management_System.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {
    private final ShiftService shiftService;


    @PostMapping("/assign")
    public ResponseEntity<String> assignShift(@RequestBody ShiftRequest shiftRequest, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        String response = shiftService.assignShiftToNurse(
                shiftRequest.getNurseNationalId(),
                shiftRequest.getStartTime(),
                shiftRequest.getEndTime(),
                shiftRequest.getShiftType()
        );

        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{shiftId}")
    public ResponseEntity<String> updateShift(@PathVariable Long shiftId,
                                              @RequestBody ShiftRequest shiftRequest,
                                              Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        String response = shiftService.updateShift(shiftId, shiftRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-nurse")
    public ResponseEntity<List<Shift>> getShiftsByNurse(@RequestParam String nationalId, Authentication authentication) {
        boolean isAdminOrNurse = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN") || auth.getAuthority().equals("NURSE"));

        if (!isAdminOrNurse) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Shift> shifts = shiftService.getShiftsByNurseNationalId(nationalId);
        return ResponseEntity.ok(shifts);
    }

}
