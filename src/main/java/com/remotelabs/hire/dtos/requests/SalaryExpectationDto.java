package com.remotelabs.hire.dtos.requests;

import com.remotelabs.hire.enums.Currency;
import com.remotelabs.hire.enums.SalaryType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalaryExpectationDto {

    @NotNull(message = "Missing the salary amount")
    private BigDecimal amount;

    @NotNull(message = "Missing the salary currency")
    private Currency currency;

    @NotNull(message = "Missing the salary type")
    private SalaryType salaryType;
}
