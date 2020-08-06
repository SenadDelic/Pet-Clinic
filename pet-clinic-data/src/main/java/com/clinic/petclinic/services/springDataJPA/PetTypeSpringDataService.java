package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.PetType;
import com.clinic.petclinic.repositories.PetTypeRepository;
import com.clinic.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJPA")
public class PetTypeSpringDataService implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeSpringDataService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(pet -> petTypes.add(pet));
        return petTypes;
    }

    @Override
    public PetType findById(Integer integer) {
        return petTypeRepository.findById(integer).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        petTypeRepository.deleteById(integer);
    }
}
