package com.kareem.Hospital_Management_System.repository;

import com.kareem.Hospital_Management_System.models.Nurse;
import com.kareem.Hospital_Management_System.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByNurse(Nurse nurse);
    List<Shift> findByNurse_NationalId(String nationalId);

}
