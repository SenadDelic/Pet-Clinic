package com.clinic.petclinic.controllers;

import com.clinic.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// @RequestMapping("/vets")
@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"", "/vets/vet", "/vets/vet.html", "/vets.html"})
    public String listOfVets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/vet";
    }
}
