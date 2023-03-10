package com.remotelabs.hire.dtos.requests;

import com.remotelabs.hire.annotations.NotEmptyList;
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

    private Long mainTechnologyId;
    private Long jobRoleId;
    private BigDecimal salaryExpectation;
    private Long countryId;
    private Integer yearsOfExperience;
    private Integer noticePeriod;
    private List<Language> languages;
    private List<Long> additionalTechnologyIds;
    private List<String> keywords;
    private Map<SortCandidateBy, SortOrder> sortBy;
}
