package com.nhnacademy.security.certificate.repository;

import com.nhnacademy.security.certificate.domain.FamilyRelationship;
import com.nhnacademy.security.certificate.response.FamilyRelationshipResponse;
import com.nhnacademy.security.certificate.response.FamilySimpleDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {
    List<FamilySimpleDto> findByBaseResident_ResidentSerialNumber(int baseResidentSerialNumber);
}
