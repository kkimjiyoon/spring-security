package com.nhnacademy.security.certificate.controller;

import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CertificateController {

    private final ResidentService residentService;

    @GetMapping("/certificates")
    public String homePage(Model model, @PageableDefault(size = 5) Pageable pageable) {
        Page<Resident> residentList = residentService.getResidents(pageable);

        model.addAttribute("residentList", residentList);
        model.addAttribute("hasNext", residentList.hasNext());
        model.addAttribute("hasPrevious", residentList.hasPrevious());
        return "certificate";
    }
}
