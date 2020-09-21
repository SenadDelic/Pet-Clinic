package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeServiceMapTest {
    PetType petType;
    PetTypeServiceMap petTypeServiceMap;
    final Integer id = 1;
    final String name = "petType";

    @BeforeEach
    void setUp() {
        petType = new PetType();
        petType.setId(id);
        petType.setName(name);

        petTypeServiceMap = new PetTypeServiceMap();
        petTypeServiceMap.save(petType);
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeServiceMap.findAll();
        assertEquals(1, petTypes.size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deleteById(id);
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petTypeServiceMap.delete(petType);
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void save() {
        PetType newPetType = petTypeServiceMap.save(PetType.builder().build());
        assertNotNull(newPetType);
        assertNotNull(newPetType.getId());
    }

    @Test
    void findById() {
        PetType anotherPetType = petTypeServiceMap.findById(id);
        assertEquals(1, anotherPetType.getId());
    }
}