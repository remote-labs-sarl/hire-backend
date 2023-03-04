package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.AddCandidateDto;
import com.remotelabs.hire.dtos.requests.SearchCandidateDto;
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

import static com.remotelabs.hire.constants.Constants.AUTHORISATION;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/management/candidates")
@RequiredArgsConstructor
@Tag(name = "ManagementCandidateController")
public class MngCandidateController {

    private final CandidateService candidateService;

    @PostMapping(value = "/filter", headers = AUTHORISATION)
    @Operation(description = "Admin Getting the list of candidates. ")
    public ResponseEntity<Page<CandidateResource>> getCandidates(@RequestBody SearchCandidateDto searchCandidateDto,
                                                                 @RequestParam int page,
                                                                 @RequestParam int size) {

        return new ResponseEntity<>(candidateService.getCandidates(searchCandidateDto, page, size), OK);
    }

    @PostMapping(value = "", headers = AUTHORISATION)
    @Operation(description = "Add candidate ")
    public ResponseEntity<Void> addCandidate(@RequestBody AddCandidateDto addCandidateDto) {

        candidateService.addCandidate(addCandidateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{candidateId}", headers = AUTHORISATION)
    @Operation(description = "Update a candidate")
    public ResponseEntity<Void> updateCandidate(@PathVariable Long candidateId,
                                               @RequestBody @Valid UpdateCandidateDto updateCandidateDto){

        candidateService.updateCandidate(candidateId, updateCandidateDto);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping(value = "/{candidateId}", headers = AUTHORISATION)
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long candidateId){

        candidateService.deleteCandidate(candidateId);
        return new ResponseEntity<>(OK);
    }
}
