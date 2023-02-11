package com.remotelabs.hire.controllers.admin;

import com.remotelabs.hire.dtos.requests.AddCandidateDto;
import com.remotelabs.hire.dtos.requests.CandidateSearchDto;
import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.services.resourceservices.CandidateResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/admins/candidates")
@RequiredArgsConstructor
@Tag(name = "AdminCandidateController")
public class AdminCandidateController {

    private final CandidateResourceService candidateResourceService;

    @PostMapping("/filter")
    @Operation(description = "Admin Getting the list of candidates. ")
    public ResponseEntity<Page<CandidateResource>> getCandidates(@RequestBody CandidateSearchDto candidateSearchDto,
                                                                 @RequestParam int page,
                                                                 @RequestParam int size) {

        return new ResponseEntity<>(candidateResourceService.getCandidates(candidateSearchDto, page, size), OK);
    }

    @PostMapping("/add")
    @Operation(description = "Add candidate ")
    public ResponseEntity<Void> addCandidate(@RequestBody AddCandidateDto addCandidateDto) {

        candidateResourceService.addCandidate(addCandidateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // TODO: 11/02/2023 HEY ALINA PLEASE DO THE ENDPOINTS FOR UPDATE, DELETE GET BY ID, THEN WRITE TESTS FOR THEM
}
