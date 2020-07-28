package com.clinic.petclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private Integer id;

    public BaseEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
