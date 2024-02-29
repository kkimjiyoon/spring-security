package com.nhnacademy.security.certificate.service;


import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.repository.ResidentRepository;
import com.nhnacademy.security.certificate.request.ResidentDeathModifyRequest;
import com.nhnacademy.security.certificate.request.ResidentNameModifyRequest;
import com.nhnacademy.security.certificate.request.ResidentRegisterRequest;
import com.nhnacademy.security.certificate.response.ResidentResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;

    private final PasswordEncoder passwordEncoder;

    public ResidentServiceImpl(ResidentRepository residentRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResidentResponse getResident(int residentSerialNumber) {
        return residentRepository.findByResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public ResidentResponse registerResident(ResidentRegisterRequest request) {
        Resident resident = new Resident();
        resident.setResidentSerialNumber(request.getResidentSerialNumber());
        resident.setName(request.getName());
        resident.setResidentRegistrationNumber(request.getResidentRegistrationNumber());
        resident.setGenderCode(request.getGenderCode());
        resident.setBirthDate(request.getBirthDate());
        resident.setBirthPlaceCode(request.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(request.getRegistrationBaseAddress());
        resident.setDeathDate(null);
        resident.setDeathPlaceCode(null);
        resident.setDeathPlaceAddress(null);
        resident.setResidentId(request.getResidentId());
        resident.setResidentPassword(passwordEncoder.encode(request.getResidentPassword()));
        resident.setResidentEmail(request.getResidentEmail());

        residentRepository.save(resident);

        return new ResidentResponse(resident.getResidentSerialNumber(), resident.getName());
    }

    @Override
    public ResidentResponse modifyResidentName(int residentSerialNumber, ResidentNameModifyRequest request) {

        Resident resident = residentRepository.findById(residentSerialNumber).orElse(null);
        resident.setName(request.getName());

        residentRepository.save(resident);
        return new ResidentResponse(resident.getResidentSerialNumber(), resident.getName());
    }

    @Override
    public ResidentResponse modifyResidentDeath(int residentSerialNumber, ResidentDeathModifyRequest request) {
        Resident resident =residentRepository.findById(residentSerialNumber).orElse(null);
        resident.setDeathDate(request.getDeathDate());
        resident.setDeathPlaceCode(request.getDeathPlaceCode());
        resident.setDeathPlaceAddress(request.getDeathPlaceAddress());

        residentRepository.save(resident);
        return new ResidentResponse(resident.getResidentSerialNumber(), resident.getName());
    }

    @Override
    public void deleteResident(int residentSerialNumber) {
        residentRepository.deleteById(residentSerialNumber);
    }

}
