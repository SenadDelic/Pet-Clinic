package com.clinic.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vets")
@Controller
public class VetController {

    @RequestMapping({"", "/", "/vet", "vet.html"})
    public String listOfVets() {

        return "vets/vet";
    }
}
