package com.nhnacademy.security.certificate.service;
import com.nhnacademy.security.certificate.domain.Household;
import com.nhnacademy.security.certificate.repository.HouseholdRepository;
import com.nhnacademy.security.certificate.repository.ResidentRepository;
import com.nhnacademy.security.certificate.request.HouseholdRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("householdService")
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }
    @Override
    public Household registerHousehold(HouseholdRegisterRequest request) {
        Household household = new Household();
        household.setHouseholdSerialNumber(request.getHouseholdSerialNumber());
        household.setHouseholdResident(residentRepository.findById(request.getHouseholdResidentSerialNumber()).orElse(null));
        household.setHouseholdCompositionDate(request.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(request.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(request.getCurrentHouseMovementAddress());

        return householdRepository.save(household);
    }

    @Override
    public void deleteHousehold(int householdSerialNumber) {
        Optional<Household> household = householdRepository.findById(householdSerialNumber);

        householdRepository.delete(household.get());
    }
}
