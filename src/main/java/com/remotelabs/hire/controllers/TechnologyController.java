package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.TechnologyResource;
import com.remotelabs.hire.services.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final TechnologyService technologyService;

    @GetMapping("")
    public ResponseEntity<Page<TechnologyResource>> getTechnologies(@RequestParam int page,
                                                                    @RequestParam int size,
                                                                    @RequestParam(required = false) String keyword){

        return new ResponseEntity<>(technologyService.getTechnologies(page, size, keyword), HttpStatus.OK);
    }
}
