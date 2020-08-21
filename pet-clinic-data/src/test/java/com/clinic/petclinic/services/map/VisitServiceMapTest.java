package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.model.Pet;
import com.clinic.petclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitServiceMapTest {
    Visit visit;
    Pet pet;
    Owner owner;

    VisitServiceMap visitServiceMap;
    PetServiceMap petServiceMap;
    OwnerServiceMap ownerServiceMap;

    final Integer visitId = 1;
    final Integer ownerId = 2;
    final Integer petId = 3;

    final String description = "description";

    @BeforeEach
    void setUp() {
        visit = new Visit();
        pet = new Pet();
        owner = new Owner();

        visit.setId(visitId);
        visit.setPet(pet);
        visit.setDescription(description);
        owner.setId(ownerId);
        pet.setId(petId);
        pet.setOwner(owner);

        visitServiceMap = new VisitServiceMap();
        petServiceMap = new PetServiceMap();
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        ownerServiceMap.save(owner);
        petServiceMap.save(pet);
        visitServiceMap.save(visit);
    }

    @Test
    void findAll() {
        Set<Visit> visits = visitServiceMap.findAll();
        assertEquals(1, visits.size());
    }

    @Test
    void deleteById() {
        visitServiceMap.deleteById(visitId);
        assertEquals(0, visitServiceMap.findAll().size());
    }

    @Test
    void delete() {
        visitServiceMap.delete(visitServiceMap.findById(visitId));
        assertEquals(0, visitServiceMap.findAll().size());
    }

    @Test
    void save() {
        assertNotNull(visit);
    }

    @Test
    void findById() {
        Visit newVisit = visitServiceMap.findById(visitId);
        assertEquals(1, newVisit.getId());
    }
}