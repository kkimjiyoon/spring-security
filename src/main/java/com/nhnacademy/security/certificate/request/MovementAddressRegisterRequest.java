package com.nhnacademy.security.certificate.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementAddressRegisterRequest {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate houseMovementReportDate;
    private int householdSerialNumber;
    private String houseMovementAddress;
    private String lastAddress;
}
