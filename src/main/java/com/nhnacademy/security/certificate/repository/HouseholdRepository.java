package com.nhnacademy.security.certificate.repository;

import com.nhnacademy.security.certificate.domain.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
}
