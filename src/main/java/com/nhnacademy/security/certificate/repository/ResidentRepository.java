package com.nhnacademy.security.certificate.repository;

import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.response.ResidentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    ResidentResponse findByResidentSerialNumber(int residentSerialNumber);

    Resident findByResidentId(String residentId);

    Resident findByResidentEmail(String residentEmail);
}
