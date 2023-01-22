package com.remotelabs.hire.controllers;

import com.remotelabs.hire.BaseIntegrationTestsIT;
import com.remotelabs.hire.enums.CandidateType;
import com.remotelabs.hire.enums.Language;
import com.remotelabs.hire.filters.CandidateFilter;
import com.remotelabs.hire.repositories.TechnologyRepository;
import com.remotelabs.hire.utils.JsonCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.remotelabs.hire.enums.CandidateType.DEVELOPER;
import static com.remotelabs.hire.enums.Language.ENGLISH;
import static com.remotelabs.hire.enums.Language.FRENCH;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CandidateControllerIT extends BaseIntegrationTestsIT {

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

    private CandidateFilter createCandidateFilter() {

        CandidateFilter candidateFilter = new CandidateFilter();
        candidateFilter.setMainTechnologyId(1L);
        candidateFilter.setKeywords(Arrays.asList("GOOD", "DEDICATED", "PASSIONATE"));
        candidateFilter.setType(DEVELOPER);
        candidateFilter.setLanguages(Arrays.asList(FRENCH, ENGLISH));
        candidateFilter.setCountryId(1L);
        candidateFilter.setNoticePeriod(30);
        candidateFilter.setAdditionalTechnologies(Arrays.asList("NODEJS", "JS"));
        candidateFilter.setSalaryExpectation(BigDecimal.valueOf(5000));

        return candidateFilter;
    }
}
