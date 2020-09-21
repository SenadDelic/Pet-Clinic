package com.clinic.petclinic.services;

import com.clinic.petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CRUDService<Owner, Integer> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
