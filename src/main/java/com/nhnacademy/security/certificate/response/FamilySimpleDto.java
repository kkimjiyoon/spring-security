package com.nhnacademy.security.certificate.response;

public interface FamilySimpleDto {

    ResidentDto getBaseResident();

    ResidentDto getFamilyResident();

    String getFamilyRelationshipCode();

    interface ResidentDto {
        String getResidentSerialNumber();
        String getName();
    }
}
