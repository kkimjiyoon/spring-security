package com.nhnacademy.security.certificate.service;
import com.nhnacademy.security.certificate.request.ResidentDeathModifyRequest;
import com.nhnacademy.security.certificate.request.ResidentNameModifyRequest;
import com.nhnacademy.security.certificate.request.ResidentRegisterRequest;
import com.nhnacademy.security.certificate.response.ResidentResponse;

public interface ResidentService {
    ResidentResponse getResident(int residentSerialNumber);

    ResidentResponse registerResident(ResidentRegisterRequest residentRegisterDto);

    ResidentResponse modifyResidentName(int residentSerialNumber, ResidentNameModifyRequest residentNameModifyDto);

    ResidentResponse modifyResidentDeath(int residentSerialNumber, ResidentDeathModifyRequest residentDeathModifyDto);

    void deleteResident(int residentSerialNumber);

}
