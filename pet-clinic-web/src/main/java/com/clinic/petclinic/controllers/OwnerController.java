package com.clinic.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    @RequestMapping({"", "/", "/owner", "/owner.html"})
    public String listOfOwners() {

        return "owners/owner";
    }
}
