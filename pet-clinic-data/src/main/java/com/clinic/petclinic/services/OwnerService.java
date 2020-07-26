package com.clinic.petclinic.services;

import com.clinic.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findByLastName(String lastName);

    Owner findById(int id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}