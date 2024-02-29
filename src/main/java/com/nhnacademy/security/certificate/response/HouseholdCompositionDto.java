package com.nhnacademy.security.certificate.response;

public interface HouseholdCompositionDto {
    HouseholdDto getHousehold();

    FamilySimpleDto.ResidentDto getResident();
    interface HouseholdDto {
        int getHouseholdSerialNumber();
    }
}
