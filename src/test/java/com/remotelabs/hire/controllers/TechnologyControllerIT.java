package com.remotelabs.hire.controllers;

import com.remotelabs.hire.BaseIntegrationTestsIT;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"/scripts/InsertTechnologies.sql"}, executionPhase = BEFORE_TEST_METHOD)
class TechnologyControllerIT extends BaseIntegrationTestsIT {

    // FIXME: 10/02/2023 assertion of length always return true even when there is no data
    @Test
    void testGetAllTechnologies() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/technologies")
                        .param("page", "0")
                        .param("size", "10")
                        .param("keyword", "")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }
}
