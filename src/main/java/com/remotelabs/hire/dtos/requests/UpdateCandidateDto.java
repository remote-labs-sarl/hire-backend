package com.remotelabs.hire.dtos.requests;

import com.remotelabs.hire.entities.SalaryExpectation;
import com.remotelabs.hire.enums.Language;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class UpdateCandidateDto {

    @NotEmpty(message = "Missing required field firstName")
    private String firstName;
    @NotEmpty(message = "Missing required field middleName")
    private String middleName;
    @NotEmpty(message = "Missing required field lastName")
    private String lastName;
    private String tags;
    @NotNull(message = "Missing required field candidateType")
    private Long jobRoleId;
    @NotNull(message = "Missing required field countryId")
    private Long countryId;
    @NotNull(message = "Missing required field salaryExpectation")
    private SalaryExpectationDto salaryExpectation;
    @NotNull(message = "Missing required field yearsOfExperience")
    private Integer yearsOfExperience;
    @NotNull(message = "Missing required field noticePeriod")
    private NoticePeriodDto noticePeriod;
    private List<Language> languages;
    @NotNull(message = "Missing required field mainTechnologyId")
    private Long mainTechnologyId;
    private List<Long> additionalTechnologiesIds;
    @NotNull(message = "Missing required field loginDetails")
    private AddUserDto loginDetails;
}
