package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.AddCandidateDto;
import com.remotelabs.hire.dtos.requests.CandidateSearchDto;
import com.remotelabs.hire.dtos.requests.UpdateCandidateDto;
import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.services.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/management/candidates")
@RequiredArgsConstructor
@Tag(name = "ManagementCandidateController")
public class MngCandidateController {

    private final CandidateService candidateService;

    @PostMapping("/filter")
    @Operation(description = "Admin Getting the list of candidates. ")
    public ResponseEntity<Page<CandidateResource>> getCandidates(@RequestBody CandidateSearchDto candidateSearchDto,
                                                                 @RequestParam int page,
                                                                 @RequestParam int size) {

        return new ResponseEntity<>(candidateService.getCandidates(candidateSearchDto, page, size), OK);
    }

    @PostMapping("")
    @Operation(description = "Add candidate ")
    public ResponseEntity<Void> addCandidate(@RequestBody AddCandidateDto addCandidateDto) {

        candidateService.addCandidate(addCandidateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{candidateId}")
    @Operation(description = "Update a candidate")
    public ResponseEntity<Void> updateCandidate(@PathVariable Long candidateId,
                                               @RequestBody @Valid UpdateCandidateDto updateCandidateDto){

        candidateService.updateCandidate(candidateId, updateCandidateDto);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long candidateId){

        candidateService.deleteCandidate(candidateId);
        return new ResponseEntity<>(OK);
    }
}
