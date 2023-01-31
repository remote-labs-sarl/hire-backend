package com.remotelabs.hire.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CountryResource {

    private String code;
    private String name;
    private String dialCode;
    private String currencyName;
    private String currencySymbol;
    private String currencyCode;
    private boolean hasPostalCode;
    private BigDecimal virtualCardPrice;
}
