package com.nhnacademy.security.certificate.controller.restcontroller;

import com.nhnacademy.security.certificate.request.HouseholdCompositionRegisterRequest;
import com.nhnacademy.security.certificate.response.HouseholdCompositionDto;
import com.nhnacademy.security.certificate.response.HouseholdCompositionResponse;
import com.nhnacademy.security.certificate.service.HouseholdCompositionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("household")
@AllArgsConstructor
public class HouseholdCompositionController {

    private final HouseholdCompositionService householdCompositionService;


    @PostMapping("/{householdSerialNumber}/composition/{residentSerialNumber}")
    public HouseholdCompositionResponse registerHouseholdComposition(@PathVariable("householdSerialNumber") int householdSerialNumber, @PathVariable("residentSerialNumber") int residentSerialNumber,
                                                                     @RequestBody HouseholdCompositionRegisterRequest request) {
        return householdCompositionService.registerHouseholdComposition(householdSerialNumber, residentSerialNumber, request);
    }


    @GetMapping("/{householdSerialNumber}/composition")
    public List<HouseholdCompositionDto> getHouseholdComposition(@PathVariable("householdSerialNumber") int householdSerialNumber) {
        return householdCompositionService.getHouseholdComposition(householdSerialNumber);
    }

    @DeleteMapping("/{householdSerialNumber}/composition/{residentSerialNumber}")
    public void deleteHouseholdComposition(@PathVariable("householdSerialNumber") int householdSerialNumber, @PathVariable("residentSerialNumber") int residentSerialNumber) {
        householdCompositionService.deleteHouseholdComposition(householdSerialNumber, residentSerialNumber);
    }

}
