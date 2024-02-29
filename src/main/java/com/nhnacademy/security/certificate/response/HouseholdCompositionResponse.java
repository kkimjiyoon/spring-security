package com.nhnacademy.security.certificate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdCompositionResponse {
    private int householdSerialNumber;
    private int residentSerialNumber;
    private String householdRelationshipCode;
}
