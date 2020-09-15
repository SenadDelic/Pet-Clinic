package com.clinic.petclinic.controllers;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//import javax.validation.Valid;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private static final String FIND_OWNERS = "owners/findOwners";
    private static final String REDIRECT_OWNERS = "redirect:/owners/";
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

    @RequestMapping({"/find", "/find.html"})
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return FIND_OWNERS;
    }

    @RequestMapping()
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        if (owner.getLastName() == null)
            owner.setLastName(""); // empty string signifies broadest possible search

        // Find owners ba last name
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName() + "%");

        if (results.isEmpty()) {
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return FIND_OWNERS;
        } else if (results.size() == 1) {
            owner = results.get(0);
            return REDIRECT_OWNERS + owner.getId();
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
    public ModelAndView showOwner(@PathVariable Integer ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return REDIRECT_OWNERS + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Integer ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String procesUpdateOwnerForm(@Validated Owner owner, BindingResult bindingResult, @PathVariable Integer ownerId) {
        if (bindingResult.hasErrors())
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        else {
            owner.setId(ownerId);
            Owner saveOwner = ownerService.save(owner);
            return REDIRECT_OWNERS + saveOwner.getId();
        }
    }
}
