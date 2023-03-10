package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.UpdateCountryStatusDto;
import com.remotelabs.hire.services.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.remotelabs.hire.constants.Constants.AUTH;

@Validated
@RestController
@RequestMapping("/management/countries")
@RequiredArgsConstructor
@Tag(name = "ManagementCountryController")
public class MngCountryController {

    private final CountryService countryService;

    @PostMapping(value = "/status", headers = AUTH)
    @Operation(description = "Activate or deactivate a country")
    public ResponseEntity<Void> activateOrDeactivateCountry(@RequestBody UpdateCountryStatusDto updateDto) {

        countryService.activateDeactivateCountry(updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
