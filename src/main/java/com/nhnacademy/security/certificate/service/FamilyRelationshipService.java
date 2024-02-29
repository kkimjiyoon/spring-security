package com.nhnacademy.security.certificate.service;

import com.nhnacademy.security.certificate.domain.FamilyRelationship;
import com.nhnacademy.security.certificate.request.FamilyRelationshipModifyRequest;
import com.nhnacademy.security.certificate.request.FamilyRelationshipRegisterRequest;
import com.nhnacademy.security.certificate.response.FamilyRelationshipResponse;
import com.nhnacademy.security.certificate.response.FamilySimpleDto;

import java.util.List;

public interface FamilyRelationshipService {
    List<FamilySimpleDto> getFamilyRelationship(int baseResidentSerialNumber);
    FamilyRelationshipResponse registerFamilyRelationship(int baseResidentSerialNumber, FamilyRelationshipRegisterRequest request);
    FamilyRelationshipResponse modifyFamilyRelationship(int baseResidentSerialNumber, int familyResidentSerialNumber, FamilyRelationshipModifyRequest request);
    void deleteFamilyRelationship(int baseResidentSerialNumber, int familyResidentSerialNumber);
}
