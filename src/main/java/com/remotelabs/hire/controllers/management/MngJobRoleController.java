package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.AddJobRole;
import com.remotelabs.hire.dtos.requests.UpdateJobRole;
import com.remotelabs.hire.dtos.responses.JobRoleResource;
import com.remotelabs.hire.services.JobRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.remotelabs.hire.constants.Constants.AUTH;

@Validated
@RestController
@RequestMapping("/management/job-roles")
@RequiredArgsConstructor
@Tag(name = "ManagementJobRoleController")
public class MngJobRoleController {

    private final JobRoleService jobRoleService;

    @PostMapping(value = "", headers = AUTH)
    @Operation(description = "Add a job role")
    public ResponseEntity<Void> addJobRole(@Valid @RequestBody AddJobRole addJobRole) {

        jobRoleService.addJobRole(addJobRole);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "", headers = AUTH)
    @Operation(description = "Get all job roles")
    public ResponseEntity<Page<JobRoleResource>> getJobRoles(@RequestParam int page, @RequestParam int size) {

        return new ResponseEntity<>(jobRoleService.getJobRoles(page, size), HttpStatus.OK);
    }

    @PutMapping(value = "/{jobRoleId}", headers = AUTH)
    @Operation(description = "Update a job role")
    public ResponseEntity<Void> updateJobRole(@PathVariable Long jobRoleId, @RequestBody UpdateJobRole updateJobRole) {

        jobRoleService.updateJobRole(jobRoleId, updateJobRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobRoleId}", headers = AUTH)
    @Operation(description = "Delete job role")
    public ResponseEntity<Void> deleteJobRole(Long jobRoleId) {

        jobRoleService.deleteJobRole(jobRoleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
