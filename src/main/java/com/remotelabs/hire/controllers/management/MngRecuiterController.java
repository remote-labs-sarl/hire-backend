package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.*;
import com.remotelabs.hire.dtos.responses.RecruiterResource;
import com.remotelabs.hire.services.RecruiterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.remotelabs.hire.constants.Constants.AUTH;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/management/recruiters")
@RequiredArgsConstructor
@Tag(name = "ManagementRecruiterController")
public class MngRecuiterController {

    private final RecruiterService recruiterService;

    @PostMapping(value = "/filter", headers = AUTH)
    @Operation(description = "Admin Getting the list of recruiters. ")
    public ResponseEntity<Page<RecruiterResource>> getCandidates(@RequestParam int page,
                                                                 @RequestParam int size) {

        return new ResponseEntity<>(recruiterService.getAllRecruiters(page, size), OK);
    }

    @PostMapping(value = "", headers = AUTH)
    @Operation(description = "Add recruiter ")
    public ResponseEntity<Void> addRecruiter(@RequestBody AddRecruiterDto addRecruiterDto) {

        recruiterService.addRecruiter(addRecruiterDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{recruiterId}", headers = AUTH)
    @Operation(description = "Update a recruiter")
    public ResponseEntity<Void> updateRecruiter(@PathVariable Long recruiterId,
                                               @RequestBody @Valid UpdateRecruiterDto updateRecruiterDto){

        recruiterService.updateRecruiter(recruiterId, updateRecruiterDto);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping(value = "/{recruiterId}", headers = AUTH)
    public ResponseEntity<Void> deleteRecruitier(@PathVariable Long recruiterId){

        recruiterService.deleteRecruiter(recruiterId);
        return new ResponseEntity<>(OK);
    }
}
