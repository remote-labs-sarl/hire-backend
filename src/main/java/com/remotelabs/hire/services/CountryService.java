package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.CountryConverter;
import com.remotelabs.hire.dtos.responses.CountryResource;
import com.remotelabs.hire.entities.Country;
import com.remotelabs.hire.repositories.CountryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryConverter countryConverter;

    @Transactional
    public List<CountryResource> findActiveCountries() {

        List<Country> countries = countryRepository.findActiveCountries();
        return countries.stream().map(countryConverter::convert).collect(Collectors.toList());
    }
}
