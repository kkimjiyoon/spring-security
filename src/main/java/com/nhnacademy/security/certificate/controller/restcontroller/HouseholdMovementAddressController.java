package com.nhnacademy.security.certificate.controller.restcontroller;

import com.nhnacademy.security.certificate.request.MovementAddressModifyRequest;
import com.nhnacademy.security.certificate.request.MovementAddressRegisterRequest;
import com.nhnacademy.security.certificate.service.HouseholdMovementAddressService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("household")
public class HouseholdMovementAddressController {

    private final HouseholdMovementAddressService householdMovementAddressService;

    public HouseholdMovementAddressController(HouseholdMovementAddressService householdMovementAddressService) {
        this.householdMovementAddressService = householdMovementAddressService;
    }

    @PostMapping("/{householdSerialNumber}/movement")
    public ResponseEntity<Void> registerHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber, @RequestBody MovementAddressRegisterRequest request) {
        householdMovementAddressService.registerHouseholdMovementAddress(householdSerialNumber, request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<Void> modifyHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber, @PathVariable("reportDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate
    , @RequestBody MovementAddressModifyRequest request) {
        householdMovementAddressService.modifyHouseholdMovementAddress(householdSerialNumber, reportDate, request);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    public void deleteHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber, @PathVariable("reportDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate) {
        householdMovementAddressService.deleteHouseholdMovementAddress(householdSerialNumber, reportDate);
    }
}
