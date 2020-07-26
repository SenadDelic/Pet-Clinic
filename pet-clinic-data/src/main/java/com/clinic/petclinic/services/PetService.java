package com.clinic.petclinic.services;

import com.clinic.petclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(int id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
