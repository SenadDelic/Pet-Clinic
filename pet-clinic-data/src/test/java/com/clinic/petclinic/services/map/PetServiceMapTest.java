package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {
    PetServiceMap petServiceMap;
    Pet pet;
    final String name = "petName";
    final Integer id = 1;

    @BeforeEach
    void setUp() {
        pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        petServiceMap = new PetServiceMap();
        petServiceMap.save(pet);
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petServiceMap.findAll();
        assertEquals(1, petSet.size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(pet.getId());
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(id));
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void save() {
        Pet anotherPet = petServiceMap.save(Pet.builder().build());
        assertNotNull(anotherPet);
        assertNotNull(anotherPet.getId());
    }

    @Test
    void findById() {
        Pet findPet = petServiceMap.findById(id);
        assertEquals(1, findPet.getId());
    }
}