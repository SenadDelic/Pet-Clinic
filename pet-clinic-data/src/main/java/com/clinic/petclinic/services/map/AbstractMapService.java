package com.clinic.petclinic.services.map;

import com.clinic.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Integer> {
    protected Map<Integer, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    // Check are object and id null, if not get the id and store it in the map
    T save(T object) {
        if (object != null) {
            if (object.getId() == null)
                object.setId(getNextId());

            map.put(object.getId(), object);
        } else
            throw new RuntimeException("Object can't be null :3");
        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    // If id is 5 set it to 6 :3
    private Integer getNextId() {
        Integer nextId = null;
        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException r) {
            nextId = 1;
        }
        return nextId;
    }
}
