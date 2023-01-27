package com.remotelabs.hire.dtos.filters;

import com.remotelabs.hire.enums.CandidateType;
import com.remotelabs.hire.enums.Language;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CandidateFilter {

    @NotNull(message = "Missing field mainTechnologyId")
    private Long mainTechnologyId;

    @NotNull(message = "Missing field type")
    private CandidateType type;

    @NotNull(message = "Missing field salaryExpectation")
    private BigDecimal salaryExpectation;
    @NotNull(message = "Missing field countryId")
    private Long countryId;
    private Integer yearsOfExperience;
    private Integer noticePeriod;
    private List<Language> languages;
    private List<String> additionalTechnologies;
    private List<String> keywords;
}
