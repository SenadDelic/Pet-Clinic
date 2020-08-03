package com.clinic.petclinic.bootstrap;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.model.PetType;
import com.clinic.petclinic.model.Vet;
import com.clinic.petclinic.services.OwnerService;
import com.clinic.petclinic.services.PetTypeService;
import com.clinic.petclinic.services.VetService;
import com.clinic.petclinic.services.map.OwnerServiceMap;
import com.clinic.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        ownerService.save(owner);

        Owner owner1 = new Owner();
        owner1.setFirstName("Fiona");
        owner1.setLastName("Naka");
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
