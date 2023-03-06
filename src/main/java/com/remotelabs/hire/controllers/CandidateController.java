package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.dtos.requests.SearchCandidateDto;
import com.remotelabs.hire.services.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping("/public/candidates")
@RequiredArgsConstructor
@Tag(name = "CandidateController")
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("")
    @Operation(summary = "Get the list of candidates. ", description = "Should return a list of available candidates")
    public ResponseEntity<Page<CandidateResource>> getCandidates(@RequestBody @Valid SearchCandidateDto searchDto,
                                                                 @RequestParam int page,
                                                                 @RequestParam int size) {

        return new ResponseEntity<>(candidateService.getCandidates(searchDto, page, size), OK);
    }
}
