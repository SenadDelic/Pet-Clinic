package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Pet;
import com.clinic.petclinic.repositories.PetRepository;
import com.clinic.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJPA")
public class PetSpringDataService implements PetService {
    private final PetRepository petRepository;

    public PetSpringDataService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Integer integer) {
        return petRepository.findById(integer).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        petRepository.deleteById(integer);
    }
}
