package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.CandidateResource;
import com.remotelabs.hire.dtos.TechnologyResource;
import com.remotelabs.hire.enums.CandidateType;
import com.remotelabs.hire.filters.CandidateFilter;
import com.remotelabs.hire.services.CandidateService;
import com.remotelabs.hire.services.TechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
@Tag(name = "CandidateController")
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/filter")
    @Operation(summary = "Get the list of candidates. ", description = "Should return a list of available candidates")
    public ResponseEntity<Page<CandidateResource>> getCandidates(@RequestBody CandidateFilter candidateFilter,
                                                                 @RequestParam int page,
                                                                 @RequestParam int size) {

        return new ResponseEntity<>(candidateService.getCandidates(candidateFilter, page, size), HttpStatus.OK);
    }
}
