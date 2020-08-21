package com.clinic.petclinic.controllers;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.model.Pet;
import com.clinic.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * @param dataBinder data binding from web request parameters to JavaBean objects.
     */
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        // Mark id as disallowed for binding
        dataBinder.setDisallowedFields("id");
    }

   /* @RequestMapping({"", "/", "/owner", "/owner.html"})
    public String listOfOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/ownersList";
    }*/


    @RequestMapping({"/find", "/find.html"})
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @RequestMapping()
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        if (owner.getLastName() == null)
            owner.setLastName(""); // empty string signifies broadest possible search

        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());
        if (results.isEmpty()) {
            bindingResult.rejectValue("lastName", "notFound", "not Found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    /**
     * Display an owner
     *
     * @param ownerId the id of the owner to display
     * @return ModelMap with the model attributes for the view
     */
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Integer ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
