package com.nhnacademy.security.certificate.request;

import lombok.Data;

@Data
public class LoginRequest {
    private final String id;
    private final String password;
}
