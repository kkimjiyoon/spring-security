package com.nhnacademy.security.certificate.service;

import com.nhnacademy.security.certificate.domain.Household;
import com.nhnacademy.security.certificate.domain.HouseholdMovementAddress;
import com.nhnacademy.security.certificate.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.security.certificate.repository.HouseholdRepository;
import com.nhnacademy.security.certificate.request.MovementAddressModifyRequest;
import com.nhnacademy.security.certificate.request.MovementAddressRegisterRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HouseholdMovementAddressServiceImpl implements HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;

    public HouseholdMovementAddressServiceImpl(HouseholdMovementAddressRepository householdMovementAddressRepository, HouseholdRepository householdRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
        this.householdRepository = householdRepository;
    }
    @Override
    public void registerHouseholdMovementAddress(int householdSerialNumber, MovementAddressRegisterRequest request) {
        Household household = householdRepository.findById(householdSerialNumber).orElse(null);

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();

        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();
        pk.setHouseholdSerialNumber(householdSerialNumber);
        pk.setHouseMovementReportDate(request.getHouseMovementReportDate());

        householdMovementAddress.setPk(pk);
        householdMovementAddress.setHousehold(household);
        householdMovementAddress.setHouseMovementAddress(request.getHouseMovementAddress());
        householdMovementAddress.setLastAddress(request.getLastAddress());

        householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public void modifyHouseholdMovementAddress(int householdSerialNumber, LocalDate reportDate, MovementAddressModifyRequest request) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(householdSerialNumber, reportDate);
        HouseholdMovementAddress householdMovementAddress = householdMovementAddressRepository.findById(pk).orElse(null);

        householdMovementAddress.setHouseMovementAddress(request.getHouseMovementAddress());
        householdMovementAddress.setLastAddress(request.getLastAddress());

        householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public void deleteHouseholdMovementAddress(int householdSerialNumber, LocalDate reportDate) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(householdSerialNumber, reportDate);

        householdMovementAddressRepository.deleteById(pk);
    }
}
