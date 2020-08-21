package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.repositories.OwnerRepository;
import com.clinic.petclinic.repositories.PetRepository;
import com.clinic.petclinic.repositories.PetTypeRepository;
import com.clinic.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJPA") // fix found 2 beans
public class OwnerSpringDataService implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSpringDataService(OwnerRepository ownerRepository, PetRepository petRepository,
                                  PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Integer integer) {
        return ownerRepository.findById(integer).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        ownerRepository.deleteById(integer);
    }
}
