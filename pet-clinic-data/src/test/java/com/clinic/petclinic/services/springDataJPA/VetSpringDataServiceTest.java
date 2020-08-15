package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Speciality;
import com.clinic.petclinic.model.Vet;
import com.clinic.petclinic.repositories.VetRepository;
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
class VetSpringDataServiceTest {
    @Mock
    VetRepository vetRepository;
    @InjectMocks
    VetSpringDataService vetSpringDataService;

    Vet vet;
    final Integer ID = 1;
    final String NAME = "Vet";

    @BeforeEach
    void setUp() {
        vet = new Vet();
        vet.setId(ID);
        vet.setFirstName(NAME);
    }

    @Test
    void findAll() {
        Set<Vet> vets = new HashSet<>();
        vets.add(vet);

        Mockito.when(vetRepository.findAll()).thenReturn(vets);
        Set<Vet> tempVet = vetSpringDataService.findAll();

        assertNotNull(tempVet);
    }

    @Test
    void findById() {
        Mockito.when(vetRepository.findById(Mockito.any())).thenReturn(Optional.of(vet));
        Vet tempVet = vetSpringDataService.findById(ID);

        assertNotNull(tempVet);
    }

    @Test
    void save() {
        Vet tempVet = Vet.builder().build();
        Mockito.when(vetRepository.save(Mockito.any())).thenReturn(tempVet);
        Vet saveVet = vetSpringDataService.save(tempVet);

        assertNotNull(saveVet);
    }

    @Test
    void delete() {
        vetSpringDataService.delete(vet);
        assertEquals(0, vetSpringDataService.findAll().size());
    }

    @Test
    void deleteById() {
        vetSpringDataService.deleteById(vet.getId());
        assertEquals(0, vetSpringDataService.findAll().size());
    }
}