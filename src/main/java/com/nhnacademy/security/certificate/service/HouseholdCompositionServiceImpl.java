package com.nhnacademy.security.certificate.service;

import com.nhnacademy.security.certificate.domain.Household;
import com.nhnacademy.security.certificate.domain.HouseholdCompositionResident;
import com.nhnacademy.security.certificate.domain.HouseholdMovementAddress;
import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.security.certificate.repository.HouseholdRepository;
import com.nhnacademy.security.certificate.repository.ResidentRepository;
import com.nhnacademy.security.certificate.request.HouseholdCompositionRegisterRequest;
import com.nhnacademy.security.certificate.response.HouseholdCompositionDto;
import com.nhnacademy.security.certificate.response.HouseholdCompositionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HouseholdCompositionServiceImpl implements HouseholdCompositionService {

    //    insert into household_composition_resident values(1, 4, '20091002', '본인', '세대분리');
//    insert into household_composition_resident values(1, 5, '20100215', '배우자', '전입');
//    insert into household_composition_resident values(1, 7, '20120317', '자녀', '출생등록');
//    insert into household_composition_resident values(1, 6, '20151129', '동거인', '전입');

    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    @Override
    public List<HouseholdCompositionDto> getHouseholdComposition(int householdSerialNumber) {
        return householdCompositionResidentRepository.findByHousehold_HouseholdSerialNumber(householdSerialNumber);
    }

    @Override
    public HouseholdCompositionResponse registerHouseholdComposition(int householdSerialNumber, int residentSerialNumber, HouseholdCompositionRegisterRequest request) {

        Household household = householdRepository.findById(householdSerialNumber).orElse(null);
        Resident resident = residentRepository.findById(residentSerialNumber).orElse(null);

        HouseholdCompositionResident.Pk pk = new HouseholdCompositionResident.Pk();
        pk.setHouseholdSerialNumber(householdSerialNumber);
        pk.setResidentSerialNumber(residentSerialNumber);

        HouseholdCompositionResident householdCompositionResident = new HouseholdCompositionResident();
        householdCompositionResident.setPk(pk);
        householdCompositionResident.setHousehold(household);
        householdCompositionResident.setResident(resident);
        householdCompositionResident.setReportDate(request.getReportDate());
        householdCompositionResident.setHouseholdRelationshipCode(request.getHouseholdRelationshipCode());
        householdCompositionResident.setHouseholdCompositionChangeReasonCode(request.getHouseholdCompositionChangeReasonCode());

        householdCompositionResidentRepository.save(householdCompositionResident);

        return new HouseholdCompositionResponse(householdSerialNumber, residentSerialNumber, householdCompositionResident.getHouseholdRelationshipCode());
    }

    @Override
    public void deleteHouseholdComposition(int householdSerialNumber, int residentSerialNumber) {
        HouseholdCompositionResident.Pk pk = new HouseholdCompositionResident.Pk(householdSerialNumber, residentSerialNumber);
        householdCompositionResidentRepository.deleteById(pk);
    }
}
