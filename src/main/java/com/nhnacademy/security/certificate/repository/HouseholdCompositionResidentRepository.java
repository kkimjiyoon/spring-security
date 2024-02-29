package com.nhnacademy.security.certificate.repository;

import com.nhnacademy.security.certificate.domain.HouseholdCompositionResident;
import com.nhnacademy.security.certificate.response.HouseholdCompositionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk> {
    List<HouseholdCompositionDto> findByHousehold_HouseholdSerialNumber(int householdSerialNumber);
}
