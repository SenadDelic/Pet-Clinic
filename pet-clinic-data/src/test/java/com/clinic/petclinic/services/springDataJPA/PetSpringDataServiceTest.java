package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Pet;
import com.clinic.petclinic.model.PetType;
import com.clinic.petclinic.repositories.PetRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetSpringDataServiceTest {
    @Mock
    PetRepository petRepository;
    @InjectMocks
    PetSpringDataService petSpringDataService;

    Pet pet;
    final Integer ID = 1;
    final String NAME = "Pet Name";

    @BeforeEach
    void setUp() {
        pet = new Pet();
        pet.setId(ID);
        pet.setName(NAME);
        pet.setPetType(new PetType());
    }

    @Test
    void findAll() {
        Set<Pet> pets = new HashSet<>();
        pets.add(pet);

        Mockito.when(petRepository.findAll()).thenReturn(pets);
        Set<Pet> findPets = petSpringDataService.findAll();

        assertNotNull(findPets);
        assertEquals(1, findPets.size());
    }

    @Test
    void findById() {
        Mockito.when(petRepository.findById(Mockito.any())).thenReturn(Optional.of(pet));
        Pet tempPet = petSpringDataService.findById(ID);

        assertNotNull(tempPet);
    }

    @Test
    void save() {
        Pet tempPet = Pet.builder().build();
        Mockito.when(petRepository.save(Mockito.any())).thenReturn(tempPet);
        Pet savePet = petSpringDataService.save(tempPet);

        assertNotNull(savePet);
    }

    @Test
    void delete() {
        petSpringDataService.delete(pet);

        assertEquals(0, petSpringDataService.findAll().size());
    }

    @Test
    void deleteById() {
        petSpringDataService.deleteById(ID);

        assertEquals(0, petSpringDataService.findAll().size());
    }
}