package com.remotelabs.hire.controllers;

import com.remotelabs.hire.BaseIntegrationTestsIT;
import com.remotelabs.hire.dtos.filters.CandidateFilter;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.repositories.CandidateRepository;
import com.remotelabs.hire.utils.JsonCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.remotelabs.hire.enums.CandidateType.DEVELOPER;
import static com.remotelabs.hire.enums.Language.ENGLISH;
import static com.remotelabs.hire.enums.Language.FRENCH;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CandidateControllerIT extends BaseIntegrationTestsIT {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    void testCandidateFilter() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/candidates/filter")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonCreator.convertToJson(createCandidateFilter()))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void testAllCandidates() {

        List<Candidate> candidates = candidateRepository.findAll();
        Assertions.assertTrue(candidates.iterator().hasNext());
    }

    private CandidateFilter createCandidateFilter() {

        CandidateFilter candidateFilter = new CandidateFilter();
        candidateFilter.setMainTechnologyId(1L);
//        candidateFilter.setType(DEVELOPER);
        candidateFilter.setSalaryExpectation(BigDecimal.valueOf(1000));
//        candidateFilter.setCountryId(50L);
        candidateFilter.setYearsOfExperience(5);
//        candidateFilter.setNoticePeriod(30);
        candidateFilter.setLanguages(Arrays.asList(FRENCH, ENGLISH));
//        candidateFilter.setAdditionalTechnologies(Arrays.asList("NODEJS", "JS"));
//        candidateFilter.setKeywords(Arrays.asList("GOOD", "DEDICATED", "PASSIONATE", "KIND"));
        return candidateFilter;
    }
}
