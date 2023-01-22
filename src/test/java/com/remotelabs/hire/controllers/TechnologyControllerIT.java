package com.remotelabs.hire.controllers;

import com.remotelabs.hire.BaseIntegrationTestsIT;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.repositories.TechnologyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TechnologyControllerIT extends BaseIntegrationTestsIT {

    @Autowired
    private TechnologyRepository technologyRepository;
    @Test
    void testGetAllTechnologies() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/technologies")
                        .param("page", "0")
                        .param("size", "10")
                        .param("keyword", "")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testAllTechnologies(){

        List<Technology> technologies = technologyRepository.findAll();

        Assertions.assertFalse(technologies.iterator().hasNext());
    }
}
