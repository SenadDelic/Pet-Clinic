package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityServiceMapTest {
    Speciality speciality;
    SpecialityServiceMap specialityServiceMap;
    final Integer id = 1;
    final String description = "Speciality description";

    @BeforeEach
    void setUp() {
        speciality = new Speciality();
        speciality.setId(id);
        speciality.setDescription(description);

        specialityServiceMap = new SpecialityServiceMap();
        specialityServiceMap.save(speciality);
    }

    @Test
    void findAll() {
        Set<Speciality> specialities = specialityServiceMap.findAll();
        assertEquals(1, specialities.size());
    }

    @Test
    void deleteById() {
        specialityServiceMap.deleteById(speciality.getId());
        assertEquals(0, specialityServiceMap.findAll().size());
    }

    @Test
    void delete() {
        specialityServiceMap.delete(specialityServiceMap.findById(id));
        assertEquals(0, specialityServiceMap.findAll().size());
    }

    @Test
    void save() {
        Speciality speciality = specialityServiceMap.save(Speciality.builder().build());
        assertNotNull(speciality);
    }

    @Test
    void findById() {
        Speciality findSpecialityById = specialityServiceMap.findById(id);
        assertEquals(1, findSpecialityById.getId());
    }
}