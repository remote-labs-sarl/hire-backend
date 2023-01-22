package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.CountryConverter;
import com.remotelabs.hire.dtos.CountryResource;
import com.remotelabs.hire.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryConverter countryConverter;

    public List<CountryResource> findActiveCountries() {

        return countryConverter.convertListOfCountriesToResources(countryRepository.findActiveCountries());
    }
}
