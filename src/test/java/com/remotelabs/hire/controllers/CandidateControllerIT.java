package com.remotelabs.hire.controllers;

import com.remotelabs.hire.BaseIntegrationIT;
import com.remotelabs.hire.dtos.requests.CandidateSearchDto;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.enums.SortCandidateBy;
import com.remotelabs.hire.enums.SortOrder;
import com.remotelabs.hire.repositories.CandidateRepository;
import com.remotelabs.hire.utils.JsonCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
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
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"/scripts/InsertCountries.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/scripts/InsertTechnologies.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/scripts/InsertCandidates.sql"}, executionPhase = BEFORE_TEST_METHOD)
class CandidateControllerIT extends BaseIntegrationIT {

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

    private CandidateSearchDto createCandidateFilter() {

        CandidateSearchDto candidateSearchDto = new CandidateSearchDto();
        candidateSearchDto.setMainTechnologyId(1L);
        candidateSearchDto.setType(DEVELOPER);
        candidateSearchDto.setSalaryExpectation(BigDecimal.valueOf(1000));
        candidateSearchDto.setCountryId(50L);
        candidateSearchDto.setYearsOfExperience(5);
        candidateSearchDto.setNoticePeriod(30);
        candidateSearchDto.setLanguages(Arrays.asList(FRENCH, ENGLISH));
        candidateSearchDto.setAdditionalTechnologyIds(Arrays.asList(1L, 2L));
        candidateSearchDto.setKeywords(Arrays.asList("GOOD", "DEDICATED", "PASSIONATE", "KIND"));
        Map<SortCandidateBy, SortOrder> sortOrderMap = new HashMap<>();
        sortOrderMap.put(SortCandidateBy.FIRSTNAME, SortOrder.ASC);
        candidateSearchDto.setSortBy(sortOrderMap);
        return candidateSearchDto;
    }
}
