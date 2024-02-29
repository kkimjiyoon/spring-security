package com.nhnacademy.security.certificate.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentRegisterRequest {
    private  int residentSerialNumber;
    private  String name;
    private  String residentRegistrationNumber;
    private  String genderCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Seoul")
    private LocalDateTime birthDate;
    private  String birthPlaceCode;
    private  String registrationBaseAddress;

    // 아이디 비밀번호 이메일 추가
    private String residentId;
    private String residentPassword;
    private String residentEmail;
}
