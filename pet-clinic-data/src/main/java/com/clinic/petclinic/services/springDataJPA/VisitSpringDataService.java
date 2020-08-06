package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Visit;
import com.clinic.petclinic.repositories.VisitRepository;
import com.clinic.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJPA")
public class VisitSpringDataService implements VisitService {
    private final VisitRepository visitRepository;

    public VisitSpringDataService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Integer integer) {
        return visitRepository.findById(integer).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        visitRepository.deleteById(integer);
    }
}
