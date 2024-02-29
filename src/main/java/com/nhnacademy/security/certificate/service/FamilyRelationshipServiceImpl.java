package com.nhnacademy.security.certificate.service;
import com.nhnacademy.security.certificate.domain.FamilyRelationship;
import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.repository.FamilyRelationshipRepository;
import com.nhnacademy.security.certificate.repository.ResidentRepository;
import com.nhnacademy.security.certificate.request.FamilyRelationshipModifyRequest;
import com.nhnacademy.security.certificate.request.FamilyRelationshipRegisterRequest;
import com.nhnacademy.security.certificate.response.FamilyRelationshipResponse;
import com.nhnacademy.security.certificate.response.FamilySimpleDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service("familyRelationshipService")
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationshipServiceImpl (FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public List<FamilySimpleDto> getFamilyRelationship(int baseResidentSerialNumber) {
        return familyRelationshipRepository.findByBaseResident_ResidentSerialNumber(baseResidentSerialNumber);
    }

    @Override
    public FamilyRelationshipResponse registerFamilyRelationship(int baseResidentSerialNumber, FamilyRelationshipRegisterRequest request) {
        Resident baseResident = residentRepository.findById(baseResidentSerialNumber).orElse(null);
        Resident familyResident = residentRepository.findById(request.getFamilyResidentSerialNumber()).orElse(null);

        FamilyRelationship familyRelationship = new FamilyRelationship();
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();

        pk.setBaseResidentSerialNumber(baseResidentSerialNumber);
        pk.setFamilyResidentSerialNumber(request.getFamilyResidentSerialNumber());

        familyRelationship.setPk(pk);
        familyRelationship.setBaseResident(baseResident);
        familyRelationship.setFamilyResident(familyResident);
        familyRelationship.setFamilyRelationshipCode(request.getFamilyRelationshipCode());

        familyRelationshipRepository.save(familyRelationship);

        return new FamilyRelationshipResponse(familyRelationship.getBaseResident().getResidentSerialNumber(), familyRelationship.getFamilyResident().getResidentSerialNumber(), familyRelationship.getFamilyRelationshipCode());
    }

    @Override
    public FamilyRelationshipResponse modifyFamilyRelationship(int baseResidentSerialNumber, int familyResidentSerialNumber, FamilyRelationshipModifyRequest request) {
        FamilyRelationship familyRelationship = familyRelationshipRepository.findById(new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber))
                .orElseThrow(() -> new EntityNotFoundException("FamilyRelationship not found"));

        familyRelationship.setFamilyRelationshipCode(request.getFamilyRelationshipCode());

        familyRelationshipRepository.save(familyRelationship);

        return new FamilyRelationshipResponse(familyRelationship.getBaseResident().getResidentSerialNumber(), familyRelationship.getFamilyResident().getResidentSerialNumber(), familyRelationship.getFamilyRelationshipCode());
    }

    @Override
    public void deleteFamilyRelationship(int baseResidentSerialNumber, int familyResidentSerialNumber) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber);
        Optional<FamilyRelationship> familyRelationship = familyRelationshipRepository.findById(pk);

        familyRelationshipRepository.delete(familyRelationship.get());
    }
}
