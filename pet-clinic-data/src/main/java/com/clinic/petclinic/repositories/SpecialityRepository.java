package com.clinic.petclinic.repositories;

import com.clinic.petclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Integer> {
}
