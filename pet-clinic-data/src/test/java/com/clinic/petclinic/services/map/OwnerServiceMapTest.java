package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    Owner owner;
    final Integer ownerId = 1;
    final String lastName = "Delic";

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);

        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        // Save owner
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(1));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(1);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Owner findOwner = ownerServiceMap.findById(1);
        assertEquals(1, findOwner.getId());
    }

    @Test
    void save() {
        // Owner owner2 = Owner.builder().build();
        Owner saveOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(saveOwner);
        assertNotNull(saveOwner.getId());
        //assertEquals(2, saveOwner.getId());
    }

    @Test
    void findByLastName() {
        Owner delic = ownerServiceMap.findByLastName(lastName);
        assertNotNull(delic);
        assertEquals(ownerId, delic.getId());
    }
}