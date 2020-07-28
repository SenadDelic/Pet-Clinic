package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.services.CRUDService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Integer> implements CRUDService<Owner, Integer> {
    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public Owner findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }
}
