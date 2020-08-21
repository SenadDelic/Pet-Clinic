package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.repositories.OwnerRepository;
import com.clinic.petclinic.repositories.PetRepository;
import com.clinic.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataServiceTest {
    // Inject a mock for an instance variable that we can use anywhere in the test class:
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSpringDataService service;

    Owner delicOwner;
    final Integer OWNER_ID = 1;
    final String LAST_NAME = "Delic";

    @BeforeEach
    void setUp() {
        delicOwner = new Owner();
        delicOwner.setId(OWNER_ID);
        delicOwner.setLastName(LAST_NAME);
    }

    @Test
    void findByLastName() {
        Mockito.when(ownerRepository.findByLastName(Mockito.any())).thenReturn(delicOwner);
        Owner delic = service.findByLastName("Delic");

        assertEquals("Delic", delic.getLastName());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(delicOwner);
        ownerSet.add(Owner.builder().build());

        Mockito.when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(Mockito.any())).thenReturn(Optional.of(delicOwner));
        Owner owner = service.findById(OWNER_ID);

        assertNotNull(owner);
    }

    @Test
    void save() {
        Owner someOwner = Owner.builder().build();
        Mockito.when(ownerRepository.save(Mockito.any())).thenReturn(someOwner);

        Owner saveOwner = service.save(someOwner);
        assertNotNull(saveOwner);
    }

    @Test
    void delete() {
        service.delete(delicOwner);
        // Check if delete() was happened
        //Mockito.verify(ownerRepository).delete(Mockito.any());

        assertEquals(0, service.findAll().size());
    }

    @Test
    void deleteById() {
        service.deleteById(OWNER_ID);

        // Check if deleteById() was happened
        //Mockito.verify(ownerRepository).deleteById(Mockito.anyInt());
        assertEquals(0, service.findAll().size());
    }
}