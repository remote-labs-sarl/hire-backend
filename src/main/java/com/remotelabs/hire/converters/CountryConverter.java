package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.CountryResource;
import com.remotelabs.hire.entities.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter {

    public CountryResource convert(Country country) {

        return CountryResource.builder()
                .hasPostalCode(country.isHasPostalCode())
                .code(country.getCode())
                .dialCode(country.getDialCode())
                .currencyCode(country.getCurrencyCode())
                .currencyName(country.getCurrencyName())
                .currencySymbol(country.getCurrencySymbol())
                .name(country.getName())
                .build();
    }
}
