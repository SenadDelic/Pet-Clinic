package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Pet;
import com.clinic.petclinic.model.Visit;
import com.clinic.petclinic.repositories.VisitRepository;
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
class VisitSpringDataServiceTest {
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSpringDataService visitSpringDataService;

    Visit visit;
    final Integer ID = 1;
    final String DESCRIPTION = "Visit description";

    @BeforeEach
    void setUp() {
        visit = new Visit();
        visit.setId(ID);
        visit.setDescription(DESCRIPTION);
        //visit.setPet(new Pet());
    }

    @Test
    void findAll() {
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(visit);

        Mockito.when(visitRepository.findAll()).thenReturn(visitSet);
        Set<Visit> saveVisit = visitSpringDataService.findAll();

        assertNotNull(saveVisit);
    }

    @Test
    void findById() {
        Mockito.when(visitRepository.findById(Mockito.any())).thenReturn(Optional.of(visit));
        Visit newVisit = visitSpringDataService.findById(visit.getId());

        assertEquals(1, newVisit.getId());
    }

    @Test
    void save() {
        Visit tempVisit = Visit.builder().build();
        Mockito.when(visitRepository.save(Mockito.any())).thenReturn(tempVisit);

        Visit saveVisit = visitSpringDataService.save(tempVisit);
        assertNotNull(saveVisit);
    }

    @Test
    void delete() {
        visitRepository.delete(visit);
        assertEquals(0, visitSpringDataService.findAll().size());
    }

    @Test
    void deleteById() {
        visitRepository.deleteById(visit.getId());
        assertEquals(0, visitSpringDataService.findAll().size());
    }
}