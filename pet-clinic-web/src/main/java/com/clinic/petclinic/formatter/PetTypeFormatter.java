package com.clinic.petclinic.formatter;

import com.clinic.petclinic.model.PetType;
import com.clinic.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

/**
 * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();
        for (PetType type : findPetTypes)
            if (type.getName().equals(text))
                return type;
        throw new ParseException("type not found " + text, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
