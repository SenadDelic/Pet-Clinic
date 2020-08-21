package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.PetType;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetTypeSpringDataServiceTest {
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    PetTypeSpringDataService petTypeSpringDataService;

    PetType petType;
    final Integer ID = 1;
    final String NAME = "Samoyed :3";

    @BeforeEach
    void setUp() {
        petType = new PetType();
        petType.setId(ID);
        petType.setName(NAME);
    }

    @Test
    void findAll() {
        Set<PetType> listOfPetTypes = new HashSet<>();
        listOfPetTypes.add(petType);

        Mockito.when(petTypeRepository.findAll()).thenReturn(listOfPetTypes);
        Set<PetType> list = petTypeSpringDataService.findAll();

        assertEquals(1, list.size());
    }

    @Test
    void findById() {
        Mockito.when(petTypeRepository.findById(Mockito.any())).thenReturn(Optional.of(petType));
        PetType tempPetType = petTypeSpringDataService.findById(ID);

        assertNotNull(tempPetType);
    }

    @Test
    void save() {
        PetType tempPetType = PetType.builder().build();
        Mockito.when(petTypeRepository.save(Mockito.any())).thenReturn(tempPetType);

        PetType type = petTypeSpringDataService.save(tempPetType);
        assertNotNull(type);
    }

    @Test
    void delete() {
        petTypeSpringDataService.delete(petType);
        assertEquals(0, petTypeSpringDataService.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeSpringDataService.deleteById(ID);
        assertEquals(0, petTypeSpringDataService.findAll().size());
    }
}