package com.nhnacademy.security.certificate.controller.restcontroller;

import com.nhnacademy.security.certificate.request.BirthReportRequest;
import com.nhnacademy.security.certificate.request.DeathReportRequest;
import com.nhnacademy.security.certificate.service.BirthDeathService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/residents")
public class BirthDeathController {
    private final BirthDeathService birthDeathService;

    public BirthDeathController(BirthDeathService birthDeathService) {
        this.birthDeathService = birthDeathService;
    }

    @PostMapping("/{serialNumber}/birth")
    public ResponseEntity<Void> registerBirthReport(@PathVariable("serialNumber") int reportResidentSerialNumber, @RequestBody BirthReportRequest request) {
        birthDeathService.registerBirth(reportResidentSerialNumber, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{serialNumber}/death")
    public ResponseEntity<Void> registerDeathReport(@PathVariable("serialNumber") int reportResidentSerialNumber,
                                                    @RequestBody DeathReportRequest request) {
        birthDeathService.registerDeath(reportResidentSerialNumber, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<Void> deleteBirthReport(@PathVariable("serialNumber") int reportResidentSerialNumber, @PathVariable("targetSerialNumber")int targetResidentSerialNumber) {
        birthDeathService.deleteBirth(reportResidentSerialNumber, targetResidentSerialNumber);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<Void> deleteDeathReport(@PathVariable("serialNumber") int reportResidentSerialNumber, @PathVariable("targetSerialNumber")int targetResidentSerialNumber) {
        birthDeathService.deleteDeath(reportResidentSerialNumber, targetResidentSerialNumber);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
