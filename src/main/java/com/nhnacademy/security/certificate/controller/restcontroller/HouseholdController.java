package com.nhnacademy.security.certificate.controller.restcontroller;

import com.nhnacademy.security.certificate.request.HouseholdRegisterRequest;
import com.nhnacademy.security.certificate.service.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/household")
public class HouseholdController {
    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<Void> registerHousehold(@RequestBody HouseholdRegisterRequest request) {
        householdService.registerHousehold(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
