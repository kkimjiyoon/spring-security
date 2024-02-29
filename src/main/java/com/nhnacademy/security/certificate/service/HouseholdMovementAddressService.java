package com.nhnacademy.security.certificate.service;

import com.nhnacademy.security.certificate.request.MovementAddressModifyRequest;
import com.nhnacademy.security.certificate.request.MovementAddressRegisterRequest;

import java.time.LocalDate;

public interface HouseholdMovementAddressService {
    void registerHouseholdMovementAddress(int householdSerialNumber, MovementAddressRegisterRequest request);
    void modifyHouseholdMovementAddress(int householdSerialNumber, LocalDate reportDate, MovementAddressModifyRequest request);

    void deleteHouseholdMovementAddress(int householdSerialNumber, LocalDate reportDate);
}
