package com.nhnacademy.security.certificate.request;

import lombok.Data;

@Data
public class FamilyRelationshipRegisterRequest {
    private int familyResidentSerialNumber; // 대상 주민
    private String familyRelationshipCode;
}
