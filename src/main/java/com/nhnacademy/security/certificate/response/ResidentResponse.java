package com.nhnacademy.security.certificate.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResidentResponse {
    private int residentSerialNumber;
    private String name;
}
