package com.remotelabs.hire.services.resourceservices;

import com.remotelabs.hire.converters.CountryConverter;
import com.remotelabs.hire.dtos.responses.CountryResource;
import com.remotelabs.hire.entities.Country;
import com.remotelabs.hire.services.CountryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryResourceService {

    private final CountryService countryService;
    private final CountryConverter countryConverter;

    @Transactional
    public List<CountryResource> findActiveCountries() {

        List<Country> countries = countryService.findActiveCountries();
        return countries.stream().map(countryConverter::convert).toList();
    }
}
