package com.clinic.petclinic.controllers;

import com.clinic.petclinic.model.Owner;
import com.clinic.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;
    // provides support for Spring MVC testing. It encapsulates all web application beans and makes them available for testing.
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().build());
        owners.add(Owner.builder().build());
        owners.add(Owner.builder().build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void listOfOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/owner"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("owners/owner"))
                .andExpect(model().attribute("owners", hasSize(3)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("notImplemented"));
    }

    @Test
    void showOwner() throws Exception {
        when(ownerService.findById(anyInt())).thenReturn(Owner.builder().build());
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("owners/ownerDetails"));
                //.andExpect(model().attribute("owner", hasProperty("id"),is(1L)); fix test
    }
}