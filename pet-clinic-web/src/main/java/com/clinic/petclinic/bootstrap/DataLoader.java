package com.clinic.petclinic.bootstrap;

import com.clinic.petclinic.model.*;
import com.clinic.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialitiesService specialitiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        //int count = petTypeService.findAll().size();
        if (petTypeService.findAll().size() == 0)
            loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCat = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saveRadiology = specialitiesService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saveSurgery = specialitiesService.save(surgery);

        Speciality dentist = new Speciality();
        dentist.setDescription("Dentist");
        Speciality saveDentist = specialitiesService.save(dentist);

        Owner owner = new Owner();
        owner.setFirstName("Micheal");
        owner.setLastName("Weston");
        owner.setAddress(owner.getFirstName() + " some address");
        owner.setCity(owner.getFirstName() + " some city");
        owner.setTelephone(owner.getFirstName() + " some telephone");

        Pet michaelPet = new Pet();
        michaelPet.setPetType(saveDog);
        michaelPet.setOwner(owner);
        michaelPet.setBirthDay(LocalDate.now());
        michaelPet.setName("Roki");
        owner.getPets().add(michaelPet);

        ownerService.save(owner);

        Owner owner1 = new Owner();
        owner1.setFirstName("Fiona");
        owner1.setLastName("Naka");
        owner1.setAddress(owner1.getFirstName() + " some address");
        owner1.setCity(owner1.getFirstName() + " some city");
        owner1.setTelephone(owner1.getFirstName() + " some telephone");

        Pet fionaPet = new Pet();
        fionaPet.setPetType(saveCat);
        fionaPet.setOwner(owner);
        fionaPet.setBirthDay(LocalDate.now());
        fionaPet.setName("HorozCat");
        owner1.getPets().add(fionaPet);

        ownerService.save(owner1);

        Visit catVisit = new Visit();
        catVisit.setPet(fionaPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Cat to the doc");

        visitService.save(catVisit);

        Vet vet = new Vet();
        vet.setFirstName("John");
        vet.setLastName("Legend");
        vet.getSpecialities().add(saveRadiology);
        vetService.save(vet);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Smith");
        vet1.getSpecialities().add(saveSurgery);
        vetService.save(vet1);
    }
}
