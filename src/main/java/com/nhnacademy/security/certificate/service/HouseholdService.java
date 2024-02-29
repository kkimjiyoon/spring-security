package com.nhnacademy.security.certificate.service;

import com.nhnacademy.security.certificate.domain.Household;
import com.nhnacademy.security.certificate.request.HouseholdRegisterRequest;

public interface HouseholdService {
    Household registerHousehold(HouseholdRegisterRequest request);
    void deleteHousehold(int householdSerialNumber);
}
