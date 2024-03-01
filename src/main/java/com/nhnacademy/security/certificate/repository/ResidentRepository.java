package com.nhnacademy.security.certificate.repository;

import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.response.ResidentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    ResidentResponse findByResidentSerialNumber(int residentSerialNumber);

    Resident findByResidentId(String residentId);

    Resident findByResidentEmail(String residentEmail);
}
