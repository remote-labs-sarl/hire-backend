package com.remotelabs.hire.controllers;

import com.remotelabs.hire.BaseIntegrationTestsIT;
import com.remotelabs.hire.dtos.requests.CandidateSearchRequest;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.enums.SortCandidateBy;
import com.remotelabs.hire.enums.SortOrder;
import com.remotelabs.hire.repositories.CandidateRepository;
import com.remotelabs.hire.utils.JsonCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.remotelabs.hire.enums.CandidateType.DEVELOPER;
import static com.remotelabs.hire.enums.Language.ENGLISH;
import static com.remotelabs.hire.enums.Language.FRENCH;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CandidateControllerIT extends BaseIntegrationTestsIT {

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
                .andExpect(jsonPath("$.length()", greaterThan(0)))
                .andExpect(status().isOk());
    }

    @Test
    void testAllCandidates() {

        List<Candidate> candidates = candidateRepository.findAll();
        Assertions.assertTrue(candidates.iterator().hasNext());
    }

    private CandidateSearchRequest createCandidateFilter() {

        CandidateSearchRequest candidateSearchRequest = new CandidateSearchRequest();
        candidateSearchRequest.setMainTechnologyId(1L);
        candidateSearchRequest.setType(DEVELOPER);
        candidateSearchRequest.setSalaryExpectation(BigDecimal.valueOf(1000));
        candidateSearchRequest.setCountryId(50L);
        candidateSearchRequest.setYearsOfExperience(5);
        candidateSearchRequest.setNoticePeriod(30);
        candidateSearchRequest.setLanguages(Arrays.asList(FRENCH, ENGLISH));
        candidateSearchRequest.setAdditionalTechnologyIds(Arrays.asList(1L, 2L));
        candidateSearchRequest.setKeywords(Arrays.asList("GOOD", "DEDICATED", "PASSIONATE", "KIND"));
        Map<SortCandidateBy, SortOrder> sortOrderMap = new HashMap<>();
        sortOrderMap.put(SortCandidateBy.FIRSTNAME, SortOrder.ASC);
        candidateSearchRequest.setSortBy(sortOrderMap);
        return candidateSearchRequest;
    }
}
