package com.nhnacademy.security.certificate.controller.restcontroller;

import com.nhnacademy.security.certificate.request.ResidentDeathModifyRequest;
import com.nhnacademy.security.certificate.request.ResidentNameModifyRequest;
import com.nhnacademy.security.certificate.request.ResidentRegisterRequest;
import com.nhnacademy.security.certificate.response.ResidentResponse;
import com.nhnacademy.security.certificate.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/residents")
public class ResidentController {
    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public ResponseEntity<ResidentResponse> registerResident(@RequestBody ResidentRegisterRequest request, BindingResult bindingResult) {
        residentService.registerResident(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<ResidentResponse> getResident(@PathVariable("serialNumber") int residentSerialNumber) {
        ResidentResponse resident = residentService.getResident(residentSerialNumber);

        return ResponseEntity.ok().body(resident);
    }

    @PutMapping("/{serialNumber}/name")
    public ResponseEntity<ResidentResponse> modifyResidentName(@PathVariable("serialNumber") int residentSerialNumber, @RequestBody ResidentNameModifyRequest request) {
        residentService.modifyResidentName(residentSerialNumber, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{serialNumber}/death")
    public ResponseEntity<ResidentResponse> modifyResidentDeath(@PathVariable("serialNumber") int residentSerialNumber, @RequestBody ResidentDeathModifyRequest request) {
        residentService.modifyResidentDeath(residentSerialNumber, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{serialNumber}")
    public void deleteResident(@PathVariable("serialNumber") int residentSerialNumber) {
        residentService.deleteResident(residentSerialNumber);
    }

}
