package com.remotelabs.hire.controllers;

import com.remotelabs.hire.BaseIntegrationTestsIT;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.repositories.TechnologyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"/scripts/InsertCountries.sql"}, executionPhase = BEFORE_TEST_METHOD)
class CountriesControllerIT extends BaseIntegrationTestsIT {

    @Test
    void testGetCountries() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/countries")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }
}
