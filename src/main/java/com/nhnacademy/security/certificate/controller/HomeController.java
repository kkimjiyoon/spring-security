package com.nhnacademy.security.certificate.controller;

import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ResidentService residentService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Resident> residentList = residentService.getResidents();

        model.addAttribute("residentList", residentList);

        return "home";
    }
}
