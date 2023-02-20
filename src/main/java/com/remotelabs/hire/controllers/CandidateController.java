package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.dtos.requests.CandidateSearchDto;
import com.remotelabs.hire.services.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
@Tag(name = "CandidateController")
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("filter")
    @Operation(summary = "Get the list of candidates. ", description = "Should return a list of available candidates")
    public ResponseEntity<Page<CandidateResource>> getCandidates(@RequestBody CandidateSearchDto candidateSearchDto,
                                                                 @RequestParam int page,
                                                                 @RequestParam int size) {

        return new ResponseEntity<>(candidateService.getCandidates(candidateSearchDto, page, size), OK);
    }
}
