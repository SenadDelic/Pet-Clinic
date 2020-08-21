package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.Vet;
import com.clinic.petclinic.services.SpecialitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetServiceMapTest {
    VetServiceMap vetServiceMap;
    Vet vet;
    final Integer id = 1;
    final String name = "SomeName";

    @BeforeEach
    void setUp() {
        vet = new Vet();
        vet.setId(id);
        vet.setFirstName(name);

        vetServiceMap = new VetServiceMap(new SpecialityServiceMap());
        vetServiceMap.save(vet);
    }

    @Test
    void findAll() {
        Set<Vet> vets = vetServiceMap.findAll();
        assertEquals(1, vets.size());
    }

    @Test
    void deleteById() {
        vetServiceMap.deleteById(id);
        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void delete() {
        vetServiceMap.delete(vetServiceMap.findById(id));
        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void save() {
       // Vet vetDoc = vetServiceMap.save(Vet.builder().build());
        assertNotNull(vet);
    }

    @Test
    void findById() {
        Vet vetDoc = vetServiceMap.findById(id);
        assertEquals(1, vetDoc.getId());
    }
}