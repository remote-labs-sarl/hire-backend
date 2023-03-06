package com.remotelabs.hire.dtos.requests;

import com.remotelabs.hire.annotations.NotEmptyList;
import com.remotelabs.hire.enums.CandidateRole;
import com.remotelabs.hire.enums.Language;
import com.remotelabs.hire.enums.SortCandidateBy;
import com.remotelabs.hire.enums.SortOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SearchCandidateDto {

    @NotNull(message = "Missing field mainTechnologyId")
    private Long mainTechnologyId;
    @NotNull(message = "Missing field role")
    private CandidateRole role;
    @NotNull(message = "Missing field salaryExpectation")
    private BigDecimal salaryExpectation;
    @NotNull(message = "Missing field countryId")
    private Long countryId;
    @NotNull(message = "Missing required field yearsOfExperience")
    private Integer yearsOfExperience;
    @NotNull(message = "Missing required field noticePeriod")
    private Integer noticePeriod;
    @NotEmptyList(message = "Missing required field languages")
    private List<Language> languages;
    private List<Long> additionalTechnologyIds;
    private List<String> keywords;
    private Map<SortCandidateBy, SortOrder> sortBy;
}
