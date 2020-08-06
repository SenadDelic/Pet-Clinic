package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Vet;
import com.clinic.petclinic.repositories.VetRepository;
import com.clinic.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJPA")
public class VetSpringDataService implements VetService {
    private final VetRepository vetRepository;

    public VetSpringDataService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vet -> vets.add(vet));
        return vets;
    }

    @Override
    public Vet findById(Integer integer) {
        return vetRepository.findById(integer).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        vetRepository.deleteById(integer);
    }
}
