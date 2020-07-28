package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.Vet;
import com.clinic.petclinic.services.CRUDService;

import java.util.Set;

public class VetServiceMap extends AbstractMapService<Vet, Integer> implements CRUDService<Vet, Integer> {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Vet findById(Integer id) {
        return super.findById(id);
    }
}
