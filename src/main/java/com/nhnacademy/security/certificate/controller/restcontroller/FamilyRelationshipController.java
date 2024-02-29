package com.nhnacademy.security.certificate.controller.restcontroller;

import com.nhnacademy.security.certificate.request.FamilyRelationshipModifyRequest;
import com.nhnacademy.security.certificate.request.FamilyRelationshipRegisterRequest;
import com.nhnacademy.security.certificate.response.FamilyRelationshipResponse;
import com.nhnacademy.security.certificate.response.FamilySimpleDto;
import com.nhnacademy.security.certificate.service.FamilyRelationshipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/residents")
public class FamilyRelationshipController {
    private final FamilyRelationshipService familyRelationshipService;

    public FamilyRelationshipController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    @PostMapping("/{serialNumber}/relationship")
    public ResponseEntity<FamilyRelationshipResponse> registerRelationship(@PathVariable("serialNumber") int baseResidentSerialNumber, @RequestBody FamilyRelationshipRegisterRequest request) {
        familyRelationshipService.registerFamilyRelationship(baseResidentSerialNumber, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{serialNumber}/relationship")
    public ResponseEntity<List<FamilySimpleDto>> getFamilyRelationship(@PathVariable("serialNumber") int baseResidentSerialNumber) {
        log.info("{}", baseResidentSerialNumber);
        List<FamilySimpleDto> familyRelationships = familyRelationshipService.getFamilyRelationship(baseResidentSerialNumber);

        return ResponseEntity.ok().body(familyRelationships);
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationshipResponse> modifyRelationship(@PathVariable("serialNumber") int baseResidentSerialNumber, @PathVariable("familySerialNumber") int familyResidentSerialNumber,  @RequestBody FamilyRelationshipModifyRequest request) {
        familyRelationshipService.modifyFamilyRelationship(baseResidentSerialNumber, familyResidentSerialNumber, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<Void> deleteRelationship(@PathVariable("serialNumber") int baseResidentSerialNumber, @PathVariable("familySerialNumber") int familyResidentSerialNumber) {
        familyRelationshipService.deleteFamilyRelationship(baseResidentSerialNumber, familyResidentSerialNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
