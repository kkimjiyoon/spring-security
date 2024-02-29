package com.nhnacademy.security.certificate.service;

import com.nhnacademy.security.certificate.request.HouseholdCompositionRegisterRequest;
import com.nhnacademy.security.certificate.response.HouseholdCompositionDto;
import com.nhnacademy.security.certificate.response.HouseholdCompositionResponse;

import java.util.List;

public interface HouseholdCompositionService {

    List<HouseholdCompositionDto> getHouseholdComposition(int householdSerialNumber);

    HouseholdCompositionResponse registerHouseholdComposition(int householdSerialNumber, int residentSerialNumber, HouseholdCompositionRegisterRequest request);
//    HouseholdCompositionResponse modifyHouseholdComposition(int householdSerialNumber, int residentSerialNumber);
    void deleteHouseholdComposition(int householdSerialNumber, int residentSerialNumber);

}
