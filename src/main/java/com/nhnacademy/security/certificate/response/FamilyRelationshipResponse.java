package com.nhnacademy.security.certificate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipResponse {
   private int baseResidentSerialNumber;
   private int familyResidentSerialNumber;
   private String familyRelationshipCode;
}
