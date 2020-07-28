package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.Pet;
import com.clinic.petclinic.services.CRUDService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Integer> implements CRUDService<Pet, Integer> {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Pet findById(Integer id) {
        return super.findById(id);
    }
}
