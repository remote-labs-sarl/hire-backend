package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.responses.TechnologyResource;
import com.remotelabs.hire.services.TechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/public/technologies")
@RequiredArgsConstructor
@Tag(name = "TechnologyController")
public class TechnologyController {

    private final TechnologyService technologyService;

    @GetMapping("")
    @Operation(summary = "Get the list of technologies. ", description = "you can also filter by a keyword")
    public ResponseEntity<Page<TechnologyResource>> getTechnologies(@RequestParam int page,
                                                                    @RequestParam int size,
                                                                    @RequestParam(required = false) String keyword){

        return new ResponseEntity<>(technologyService.getTechnologies(page, size, keyword), OK);
    }
}
