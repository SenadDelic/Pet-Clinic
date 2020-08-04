package com.clinic.petclinic.bootstrap;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.model.Pet;
import com.clinic.petclinic.model.PetType;
import com.clinic.petclinic.model.Vet;
import com.clinic.petclinic.services.OwnerService;
import com.clinic.petclinic.services.PetTypeService;
import com.clinic.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCat = petTypeService.save(cat);

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
        michaelPet.setName("Roki :3");

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
        fionaPet.setName("Umjetnik :3");

        owner1.getPets().add(fionaPet);
        ownerService.save(owner1);

        System.out.println("Loading owners");

        Vet vet = new Vet();
        vet.setFirstName("Sam");
        vet.setLastName("Axc");
        vetService.save(vet);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe deodorant :3");
        vetService.save(vet1);

        System.out.println("Loading Vets....");
    }
}
