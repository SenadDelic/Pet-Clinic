package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Speciality;
import com.clinic.petclinic.repositories.SpecialityRepository;
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
class SpecialitySpringDataServiceTest {
    @Mock
    SpecialityRepository specialityRepository;
    @InjectMocks
    SpecialitySpringDataService specialitySpringDataService;

    Speciality speciality;
    final Integer ID = 1;
    final String DESCRIPTION = "Speciality Description";

    @BeforeEach
    void setUp() {
        speciality = new Speciality();
        speciality.setId(ID);
        speciality.setDescription(DESCRIPTION);
    }

    @Test
    void findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(speciality);

        Mockito.when(specialityRepository.findAll()).thenReturn(specialities);
        Set<Speciality> tempSpeciality = specialitySpringDataService.findAll();

        assertEquals(1, tempSpeciality.size());
    }

    @Test
    void findById() {
        Mockito.when(specialityRepository.findById(Mockito.any())).thenReturn(Optional.of(speciality));
        Speciality tempSpeciality = specialitySpringDataService.findById(ID);

        assertNotNull(tempSpeciality);
    }

    @Test
    void save() {
        Speciality tempSpeciality = Speciality.builder().build();
        Mockito.when(specialityRepository.save(Mockito.any())).thenReturn(tempSpeciality);
        Speciality vetSpeciality = specialitySpringDataService.save(tempSpeciality);

        assertNotNull(vetSpeciality);
    }

    @Test
    void delete() {
        specialitySpringDataService.delete(speciality);

        assertEquals(0, specialitySpringDataService.findAll().size());
    }

    @Test
    void deleteById() {
        specialitySpringDataService.deleteById(ID);

        assertEquals(0, specialitySpringDataService.findAll().size());
    }
}