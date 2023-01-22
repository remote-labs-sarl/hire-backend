package com.remotelabs.hire.filters;

import com.remotelabs.hire.enums.CandidateType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Getter
@Setter
public class CandidateFilter {

    @NotNull
    private Long mainTechnologyId;
    private CandidateType type;
    private BigDecimal salaryExpectation;
    private Long countryId;
    private int yearsOfExperience;
    private int noticePeriod;
    private String languages;
    private String additionalTechnologies;
    private String keywords;
    private int page;
}
