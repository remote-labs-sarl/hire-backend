package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.responses.CountryResource;
import com.remotelabs.hire.services.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/public/countries")
@RequiredArgsConstructor
@Tag(name = "CountryController")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("")
    @Operation(summary = "Get the list of active countries. ", description = "Countries available for hiring")
    public ResponseEntity<List<CountryResource>> getCountries() {

        return new ResponseEntity<>(countryService.findActiveCountries(), OK);
    }
}
