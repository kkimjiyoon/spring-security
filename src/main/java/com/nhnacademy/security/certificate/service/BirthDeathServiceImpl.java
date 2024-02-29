package com.nhnacademy.security.certificate.service;

import com.nhnacademy.security.certificate.domain.BirthDeathReportResident;
import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.security.certificate.repository.ResidentRepository;
import com.nhnacademy.security.certificate.request.BirthReportRequest;
import com.nhnacademy.security.certificate.request.DeathReportRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("birthDeathService")
public class BirthDeathServiceImpl implements BirthDeathService {

    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public BirthDeathServiceImpl(BirthDeathReportResidentRepository birthDeathReportResidentRepository, ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public void registerBirth(int reportResidentSerialNumber, BirthReportRequest request) {
        Resident reportResident = residentRepository.findById(reportResidentSerialNumber).orElse(null);
        Resident targetResident = residentRepository.findById(request.getResidentSerialNumber()).orElse(null);

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();

        pk.setResidentSerialNumber(request.getResidentSerialNumber());
        pk.setBirthDeathTypeCode(request.getBirthDeathTypeCode());

        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setTargetResident(targetResident);
        birthDeathReportResident.setReportResident(reportResident);
        birthDeathReportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(request.getBirthReportQualificationsCode());
        birthDeathReportResident.setDeathReportQualificationsCode(null);
        birthDeathReportResident.setEmailAddress(request.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(request.getPhoneNumber());

        birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    public void registerDeath(int reportResidentSerialNumber, DeathReportRequest request) {
        Resident reportResident = residentRepository.findById(reportResidentSerialNumber).orElse(null);
        Resident targetResident = residentRepository.findById(request.getResidentSerialNumber()).orElse(null);

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();

        pk.setResidentSerialNumber(request.getResidentSerialNumber());
        pk.setBirthDeathTypeCode(request.getBirthDeathTypeCode());

        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setTargetResident(targetResident);
        birthDeathReportResident.setReportResident(reportResident);
        birthDeathReportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(null);
        birthDeathReportResident.setDeathReportQualificationsCode(request.getDeathReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(request.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(request.getPhoneNumber());

        birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    public void deleteBirth(int reportResidentSerialNumber, int targetResidentSerialNumber) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode("출생");
        pk.setResidentSerialNumber(targetResidentSerialNumber);

        Optional<BirthDeathReportResident> birthReport = birthDeathReportResidentRepository.findById(pk);

        birthDeathReportResidentRepository.delete(birthReport.get());
    }

    @Override
    public void deleteDeath(int reportResidentSerialNumber, int targetResidentSerialNumber) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode("사망");
        pk.setResidentSerialNumber(targetResidentSerialNumber);

        Optional<BirthDeathReportResident> deathReport = birthDeathReportResidentRepository.findById(pk);

        birthDeathReportResidentRepository.delete(deathReport.get());
    }
}
