package com.clinic.petclinic.controllers;

import com.clinic.petclinic.model.Vet;
import com.clinic.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

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

    @GetMapping("/api/vets")
    public @ResponseBody
    Set<Vet> getVetsJson() {
        return vetService.findAll();
    }
}
