package com.clinic.petclinic.services.springDataJPA;

import com.clinic.petclinic.model.Speciality;
import com.clinic.petclinic.repositories.SpecialityRepository;
import com.clinic.petclinic.services.SpecialitiesService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJPA")
public class SpecialitySpringDataService implements SpecialitiesService {
    private final SpecialityRepository specialityRepository;

    public SpecialitySpringDataService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Integer integer) {
        return specialityRepository.findById(integer).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        specialityRepository.deleteById(integer);
    }
}
