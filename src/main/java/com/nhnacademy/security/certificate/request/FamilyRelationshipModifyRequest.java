package com.nhnacademy.security.certificate.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipModifyRequest {
    private String familyRelationshipCode;
}