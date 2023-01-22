package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.CountryResource;
import com.remotelabs.hire.entities.Country;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryConverter {

    public CountryResource convertCountryToResource(Country country) {

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

    public List<CountryResource> convertListOfCountriesToResources(List<Country> countries) {

        return countries.stream().map(country -> CountryResource.builder()
                .hasPostalCode(country.isHasPostalCode())
                .dialCode(country.getDialCode())
                .code(country.getCode())
                .currencyCode(country.getCurrencyCode())
                .currencyName(country.getCurrencyName())
                .currencySymbol(country.getCurrencySymbol())
                .name(country.getName())
                .build()).collect(Collectors.toList());
    }
}
