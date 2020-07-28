package com.clinic.petclinic.services;

import com.clinic.petclinic.model.Owner;

public interface OwnerService extends CRUDService<Owner, Integer> {
    Owner findByLastName(String lastName);
}
