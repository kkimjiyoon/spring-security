package com.nhnacademy.security.certificate.service;


import com.nhnacademy.security.certificate.request.BirthReportRequest;
import com.nhnacademy.security.certificate.request.DeathReportRequest;

public interface BirthDeathService {
    void registerBirth(int reportResidentSerialNumber, BirthReportRequest request);

    void registerDeath(int reportResidentSerialNumber, DeathReportRequest request);

    void deleteBirth(int reportResidentSerialNumber, int targetResidentSerialNumber);

    void deleteDeath(int reportResidentSerialNumber, int targetResidentSerialNumber);


}
